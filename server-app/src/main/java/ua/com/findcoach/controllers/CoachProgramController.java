package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.*;
import ua.com.findcoach.domain.*;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.CycleService;
import ua.com.findcoach.services.EventService;
import ua.com.findcoach.services.ProgramService;
import ua.com.findcoach.utils.DateUtils;
import ua.com.findcoach.utils.Formatters;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/coach")
public class CoachProgramController {

	@Autowired
	private LocalizedMessageResolver messageResolver;

	@Autowired
	private CoachService coachService;

	@Autowired
	private ProgramService programService;

	@Autowired
	private CycleService cycleService;

	@Autowired
	private EventService eventService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawans.html")
	public ModelAndView receiveCoachProgramPadawans(@PathVariable String coachAlias) {
		Map<String, Object> params = new HashMap<>();
		Coach currentCoach = coachService.retrieveCurrentCoach();
		params.put("coachAlias", currentCoach.getAlias());
		List<PadawanDto> padawans = new ArrayList<>();
		currentCoach
				.getProgramList()
				.stream()
				.collect(Collectors.groupingBy(p -> p.getPadawan()))
				.entrySet().stream()
				.forEach(entry ->
				{
					PadawanDto padawanDto = new PadawanDto(
							entry.getKey().getPadawanId(),
							entry.getKey().getFirstName(),
							entry.getKey().getLastName(),
							entry.getKey().getEmail(),
							entry.getKey().getGender(),
							entry.getKey().getBirthday(),
							entry.getKey().isActive());
					entry.getValue().stream()
							.forEach(program -> padawanDto.getPadawanProgramDTOList()
									.add(new ProgramDto(program.getName()
											, program.getGoal()
											, program.getProgramId()
											, program.getStartDate()
											, program.getEndDate())));
					padawans.add(padawanDto);
				});


		params.put("padawansList", padawans);
		params.put("formatter", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		return new ModelAndView("padawan-management/padawans", params);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}.html")
	public ModelAndView programDetailPage(@PathVariable String coachAlias, @PathVariable Integer programId) {
		Map<String, Object> parameters = new HashMap<>();

		Program program = programService.findProgramById(programId);

		parameters.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
		parameters.put("programName", program.getName());
		parameters.put("programId", program.getProgramId());
		parameters.put("coachAlias", coachAlias);
		parameters.put("cycles", program.getCycles());
		parameters.put("formatter", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		parameters.put("timeFormatter", DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
		return new ModelAndView("padawan-management/programDetails", parameters);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle.html")
	public ModelAndView createProgramCyclePage(@PathVariable String coachAlias, @PathVariable Integer programId) {
		Map<String, Object> parameters = new HashMap<>();

		Program program = programService.findProgramById(programId);

		parameters.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
		parameters.put("programName", program.getName());
		parameters.put("programId", program.getProgramId());

		Cycle cycle = new Cycle();
		cycle.setName("");
		cycle.setNotes("");
		cycle.setCycleId(-1);
		cycle.setStartDate(DateUtils.dateToLocalDate(new Date()));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 3);
		cycle.setEndDate(DateUtils.longToLocalDate(cal.getTimeInMillis()));
		parameters.put("cycle", cycle);
		parameters.put("cycleAction", "Добавить");

		return new ModelAndView("padawan-management/programCycleDetails", parameters);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}.html")
	public ModelAndView updateProgramCyclePage(@PathVariable String coachAlias, @PathVariable Integer programId,
											   @PathVariable Integer cycleId) {
		Map<String, Object> parameters = new HashMap<>();

		Program program = programService.findProgramById(programId);

		parameters.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
		parameters.put("programName", program.getName());
		parameters.put("programId", program.getProgramId());
		parameters.put("cycle", cycleService.findCycleById(cycleId));
		parameters.put("cycleAction", "Изменить");

		return new ModelAndView("padawan-management/programCycleDetails", parameters);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/program/{programId}/cycle")
	@ResponseBody
	public RestResponse saveCycle(@PathVariable String coachAlias, @PathVariable Integer programId,
								  @RequestBody CycleDto cycleDto) {
		Cycle existingCycle = cycleService.findCycleById(cycleDto.getCycleId());

		Cycle cycleToSave = existingCycle == null ? new Cycle() : existingCycle;
		cycleToSave.setName(cycleDto.getName());
		cycleToSave.setNotes(cycleDto.getNotes());
		cycleToSave.setStartDate(DateUtils.longToLocalDate(cycleDto.getStartDate()));
		cycleToSave.setEndDate(DateUtils.longToLocalDate(cycleDto.getEndDate()));

		if (cycleToSave.getCycleId() != null) {
			cycleService.save(cycleToSave);
		} else {
			Program program = programService.findProgramById(programId);
			if (program.getCycles() == null) {
				program.setCycles(new ArrayList<>());
			}
			program.getCycles().add(cycleToSave);
			programService.saveProgram(program);
		}

		RestResponse response = new RestResponse();
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}/training.html")
	public ModelAndView addTrainingForm(@PathVariable String coachAlias, @PathVariable Integer programId, @PathVariable Integer cycleId) {
		Map<String, Object> parameters = new HashMap<>();

		Program program = programService.findProgramById(programId);

		parameters.put("coachAlias", coachAlias);
		parameters.put("programName", program.getName());
		parameters.put("programId", program.getProgramId());
		for (Cycle cycle : program.getCycles()) {
			if (cycle.getCycleId().compareTo(cycleId) == 0) {
				parameters.put("cycleId", cycle.getCycleId());
				parameters.put("cycleName", cycle.getName());
				break;
			}
		}

		return new ModelAndView("padawan-management/trainingDetails", parameters);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}/training")
	@ResponseBody
	public RestResponse saveNewTraining(@PathVariable String coachAlias, @PathVariable Integer programId, @PathVariable Integer cycleId,
										@RequestBody @Valid TrainingDto trainingDto, BindingResult bindingResult) {

		Program program = programService.findProgramById(programId);

		final Event event = new Event();
		event.setDescription(trainingDto.getContent());
		event.setType(EventType.TRAINING);

		List<EventRecurrence> eventRecurrences = new ArrayList<>();
		EventRecurrence recurrence = new EventRecurrence();
		recurrence.setAllDay(Boolean.FALSE);
		LocalDateTime startDateTime = LocalDateTime.parse(trainingDto.getStartDateTime(), Formatters.SIMPLE_DATE_TIME_FORMATTER);
		recurrence.setStartDate(startDateTime);
		recurrence.setEndDate(startDateTime.plusMinutes(trainingDto.getDuration()));
		recurrence.setEvent(event);

		eventRecurrences.add(recurrence);
		event.setRecurrences(eventRecurrences);

		eventService.save(event);

		program.getCycles().stream().filter(cycle -> cycle.getCycleId().compareTo(cycleId) == 0).forEach(cycle -> {
			cycle.getEvents().add(event);
			cycleService.save(cycle);
		});

		RestResponse response = new RestResponse();
		return response;
	}
}
