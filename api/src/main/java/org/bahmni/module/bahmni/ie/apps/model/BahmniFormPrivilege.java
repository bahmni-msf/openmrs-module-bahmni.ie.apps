package org.bahmni.module.bahmni.ie.apps.model;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.customdatatype.CustomValueDescriptor;

import java.io.Serializable;
import java.util.List;

public class BahmniFormPrivilege extends BaseOpenmrsData implements Serializable {

    private Integer formId;
    private String privilegeName;
    private String programWorkflowStateId;
    private Boolean editable;
    private Boolean viewable;

    public BahmniFormPrivilege(Integer formId, String privilegeName, String programWorkflowStateId, Boolean editable, Boolean viewable) {
        this.formId = formId;
        this.privilegeName = privilegeName;
        this.programWorkflowStateId = programWorkflowStateId;
        this.editable = editable;
        this.viewable = viewable;
    }
    public BahmniFormPrivilege() {
    }
    public BahmniFormPrivilege(Integer formId, String privilegeName, Boolean editable, Boolean viewable) {

        this.formId = formId;
        this.privilegeName = privilegeName;
        this.editable = editable;
        this.viewable = viewable;
        this.programWorkflowStateId = null;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getProgramWorkflowStateId() {
        return programWorkflowStateId;
    }

    public void setProgramWorkflowStateId(String programWorkflowStateId) {
        this.programWorkflowStateId = programWorkflowStateId;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getViewable() {
        return viewable;
    }

    public void setViewable(Boolean viewable) {
        this.viewable = viewable;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer integer) {

    }
}