package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.domain.PadawanStatus;
import ua.com.findcoach.services.PadawanService;
import ua.com.findcoach.exception.StatusUpdateException;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.utils.PadawanStatusHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/padawan/profile")
public class PadawanProfileController {

    @Autowired
    private LocalizedMessageResolver messageResolver;

    @Autowired
    private PadawanService padawanService;

    private static final int SINGLE_ROW = 1;


    @RequestMapping("/home.html")
    public ModelAndView padawanHomePage() throws IOException {
        Map<String, Object> params = new HashMap<>();
        Map<Enum, String> statusMap = new HashMap<>();
        Map<Enum, String> statuses = PadawanStatusHolder.getStatusMap();
        String currentStatus = padawanService.selectEmail();

        statuses
                .entrySet()
                .stream()
                .forEach(enumStringEntry ->
                                statusMap.put(enumStringEntry.getKey(), messageResolver.getMessage(enumStringEntry.getValue()))
                );


        params.put("message", messageResolver.getMessage("titlepage.welcome.padawan"));
        params.put("status", statusMap);
        params.put("currentstatus", currentStatus);

        return new ModelAndView("padawanHome", params);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/padawan/status")
    @ResponseBody
    public HttpStatus updatePadawanStatus(@RequestParam("status") String status) throws IOException, StatusUpdateException {
        int updatedRowCount = padawanService.updateStatus(PadawanStatus.valueOf(status));
        if (updatedRowCount == SINGLE_ROW) {
            return HttpStatus.OK;
        }
        throw new StatusUpdateException("Something was going wrong");
    }

}
