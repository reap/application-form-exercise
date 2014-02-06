package com.github.reap.application.ui.creation;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.reap.application.model.ApplicationFormModel;
import com.github.reap.application.model.Gender;
import com.github.reap.application.storage.ApplicationStorage;
import com.github.reap.application.ui.ApplicationStoredListener;
import com.github.reap.application.ui.creation.ApplicationFormPresenter;
import com.github.reap.application.ui.creation.ApplicationFormView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ApplicationFormPresenterTest {

    @Mock
    ApplicationFormView view;
    @Mock
    ApplicationFormModel model;
    
    @Mock ApplicationStorage storage;
    @Mock ApplicationStoredListener applicationStoredListener;
    
    @Mock ValueChangeEvent firstNameFieldChangeEvent;
    @Mock Property<String> firstNameFieldProperty;
    @Mock ValueChangeEvent lastNameFieldChangeEvent;
    @Mock Property<String> lastNameFieldProperty;
    @Mock ValueChangeEvent genderFieldChangeEvent;
    @Mock Property<Gender> genderFieldProperty;
    @Mock ValueChangeEvent reasonForApplyingFieldChangeEvent;
    @Mock Property<String> reasonForApplyingFieldProperty;
    @Mock ClickEvent submitButtonClickEvent;

    @Captor ArgumentCaptor<ValueChangeListener> genderChangeListenerCaptor;
    @Captor ArgumentCaptor<ValueChangeListener> lastNameChangeListenerCaptor;
    @Captor ArgumentCaptor<ValueChangeListener> firstNameChangeListenerCaptor;
    @Captor ArgumentCaptor<ValueChangeListener> reasonForApplyingChangeListenerCaptor;
    @Captor ArgumentCaptor<ClickListener> SubmitButtonClickListenerCaptor;

    @BeforeMethod public void initMocks() {
        MockitoAnnotations.initMocks(this);
        
        new ApplicationFormPresenter(view, storage) {
            @Override
            protected ApplicationFormModel createNewModel() {
                return model;
            }
        }.addApplicationStoredListener(applicationStoredListener);
    }
    
    @Test
    public void firstNameIsStoredToModelWhenItChanges() {
        Mockito.verify(view).addFirstNameFieldChangeListener(firstNameChangeListenerCaptor.capture());
        Mockito.when(firstNameFieldChangeEvent.getProperty()).thenReturn(firstNameFieldProperty);
        Mockito.when(firstNameFieldProperty.getValue()).thenReturn("Jaska");
        
        firstNameChangeListenerCaptor.getValue().valueChange(firstNameFieldChangeEvent);
        Mockito.verify(model).setFirstName("Jaska");
    }

    @Test
    public void lastNameIsStoredToModelWhenItChanges() {
        Mockito.verify(view).addLastNameFieldChangeListener(lastNameChangeListenerCaptor.capture());
        Mockito.when(lastNameFieldChangeEvent.getProperty()).thenReturn(lastNameFieldProperty);
        Mockito.when(lastNameFieldProperty.getValue()).thenReturn("Jokunen");
        
        lastNameChangeListenerCaptor.getValue().valueChange(lastNameFieldChangeEvent);
        Mockito.verify(model).setLastName("Jokunen");
    }

    @Test
    public void genderIsStoredToModelWhenItChanges() {
        Mockito.verify(view).addGenderFieldChangeListener(genderChangeListenerCaptor.capture());
        Mockito.when(genderFieldChangeEvent.getProperty()).thenReturn(genderFieldProperty);
        Mockito.when(genderFieldProperty.getValue()).thenReturn(Gender.MALE);
        
        genderChangeListenerCaptor.getValue().valueChange(genderFieldChangeEvent);
        Mockito.verify(model).setGender(Gender.MALE);
    }

    @Test
    public void reasonForApplyingIsStoredToModelWhenItChanges() {
        Mockito.verify(view).addReasonForApplyingFieldChangeListener(reasonForApplyingChangeListenerCaptor.capture());
        Mockito.when(reasonForApplyingFieldChangeEvent.getProperty()).thenReturn(reasonForApplyingFieldProperty);
        Mockito.when(reasonForApplyingFieldProperty.getValue()).thenReturn("koska sillan alla on kylmää...");
        
        reasonForApplyingChangeListenerCaptor.getValue().valueChange(reasonForApplyingFieldChangeEvent);
        Mockito.verify(model).setReasonForApplying("koska sillan alla on kylmää...");
    }
    
    @Test public void whenSubmitButtonIsClickedModelIsStoredToStorage() {
        Mockito.when(model.getFirstName()).thenReturn("a");
        Mockito.when(model.getLastName()).thenReturn("a");
        Mockito.when(model.getGender()).thenReturn(Gender.MALE);

        Mockito.verify(view).addSubmitButtonClickListener(SubmitButtonClickListenerCaptor.capture());
        SubmitButtonClickListenerCaptor.getValue().buttonClick(submitButtonClickEvent);
        Mockito.verify(storage).save(Mockito.same(model));
    }
    
    @Test public void whenModelIsStoredListenerGetsNotified() {
        Mockito.when(model.getFirstName()).thenReturn("a");
        Mockito.when(model.getLastName()).thenReturn("a");
        Mockito.when(model.getGender()).thenReturn(Gender.MALE);
        
        Mockito.when(storage.save(model)).thenReturn(5);
        Mockito.verify(view).addSubmitButtonClickListener(SubmitButtonClickListenerCaptor.capture());
        SubmitButtonClickListenerCaptor.getValue().buttonClick(submitButtonClickEvent);
        
        Mockito.verify(applicationStoredListener).notifyApplicationStored(5);
    }

}
