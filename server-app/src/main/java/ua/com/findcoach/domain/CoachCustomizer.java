package ua.com.findcoach.domain;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;

/**
 * Created by DENIS on 24.09.2015.
 */
public class CoachCustomizer implements DescriptorCustomizer {
    @Override
    public void customize(ClassDescriptor classDescriptor) throws Exception {
        classDescriptor.getInheritancePolicy().getClassIndicatorMapping().clear();
        classDescriptor.getInheritancePolicy().addClassIndicator(User.class, false);
        classDescriptor.getInheritancePolicy().addClassIndicator(Coach.class, true);
    }
}
