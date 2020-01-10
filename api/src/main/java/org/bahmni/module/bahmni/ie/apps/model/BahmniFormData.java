package org.bahmni.module.bahmni.ie.apps.model;

import java.util.List;

public class BahmniFormData {
    private BahmniForm bahmniForm;
    private List<FormTranslation> translations;

    public BahmniForm getBahmniForm() {
        return bahmniForm;
    }

    public void setBahmniForm(BahmniForm bahmniForm) {
        this.bahmniForm = bahmniForm;
    }

    public List<FormTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<FormTranslation> translations) {
        this.translations = translations;
    }
}
