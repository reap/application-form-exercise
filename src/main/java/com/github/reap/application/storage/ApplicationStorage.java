package com.github.reap.application.storage;

import com.github.reap.application.model.ApplicationFormModel;

public interface ApplicationStorage {

    Integer save(ApplicationFormModel model);

    ApplicationFormModel load(int applicationId);

}
