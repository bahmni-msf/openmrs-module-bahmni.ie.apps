package org.bahmni.module.bahmni.ie.apps.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bahmni.module.bahmni.ie.apps.dao.BahmniFormDao;
import org.bahmni.module.bahmni.ie.apps.dao.BahmniFormPrivilegeDao;
import org.bahmni.module.bahmni.ie.apps.model.BahmniFormPrivilege;
import org.bahmni.module.bahmni.ie.apps.model.FormTranslation;
import org.bahmni.module.bahmni.ie.apps.service.BahmniFormPrivilegesService;

import org.openmrs.api.APIException;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("bahmniFormPrivilegesService")
public class BahmniFormPrivilegesServiceImpl extends BaseOpenmrsService implements BahmniFormPrivilegesService {

    private BahmniFormPrivilegeDao bahmniFormPrivilegeDao;
    protected Log log = LogFactory.getLog(getClass());
    private BahmniFormPrivilegesService bahmniFormPrivilegesService;

    @Autowired
    public BahmniFormPrivilegesServiceImpl(BahmniFormPrivilegeDao bahmniFormPrivilegeDao) {
        this.bahmniFormPrivilegeDao = bahmniFormPrivilegeDao;
        this.bahmniFormPrivilegesService = bahmniFormPrivilegesService;
    }
    public BahmniFormPrivilegesServiceImpl(){

    }

    public BahmniFormPrivilege saveFormPrivilege(BahmniFormPrivilege toPersistFormPrivilege) throws APIException {
        System.out.println("Inside BahmniFormPrivilegesServiceImpl --****---->saveFormPrivilege "+toPersistFormPrivilege);
        if (toPersistFormPrivilege != null) {
            BahmniFormPrivilege originalFormPrivilege ;
            originalFormPrivilege = getFormPrivilege(toPersistFormPrivilege.getPrivilegeName(),toPersistFormPrivilege.getFormId());
            System.out.println("Inside BahmniFormPrivilegesServiceImpl --****---->saveFormPrivilege before if block"+toPersistFormPrivilege);
            if (originalFormPrivilege != null) {
                originalFormPrivilege.setFormId(toPersistFormPrivilege.getFormId());
                originalFormPrivilege.setPrivilegeName(toPersistFormPrivilege.getPrivilegeName());
                originalFormPrivilege.setEditable(toPersistFormPrivilege.getEditable());
                originalFormPrivilege.setViewable(toPersistFormPrivilege.getViewable());
                toPersistFormPrivilege = originalFormPrivilege;
            }
            System.out.println("Inside BahmniFormPrivilegesServiceImpl --****---->saveFormPrivilege after if block"+toPersistFormPrivilege);
            return bahmniFormPrivilegeDao.saveFormPrivilege(toPersistFormPrivilege);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<BahmniFormPrivilege> saveFormPrivileges(List<BahmniFormPrivilege> formPrivileges) {
        log.info("Inside BahmniFormPrivilegesServiceImpl -->saveFormPrivileges " + formPrivileges);
        List<BahmniFormPrivilege> resultList = new ArrayList<BahmniFormPrivilege>();
        List<BahmniFormPrivilege> privilegesList = new ArrayList<BahmniFormPrivilege>();
        List<BahmniFormPrivilege> oldPrivilegeList = new ArrayList<BahmniFormPrivilege>();

//        if (formPrivileges.size() == 1 && formPrivileges.get(0).getPrivilegeName().equalsIgnoreCase("")) {
//            oldPrivilegeList = getAllPrivilegesForForm(formPrivileges.get(1).getFormId());
//            if (oldPrivilegeList != null && !(oldPrivilegeList.isEmpty())) {
//                for (int i = 0; i < oldPrivilegeList.size(); i++) {
//                    resultList.add(bahmniFormPrivilegeDao.deleteFormPrivilege(oldPrivilegeList.get(i)));
//                }
//            }
//            return resultList;
//        } else {

            Iterator privilegeItr = formPrivileges.iterator();
            int formId = 0;
            log.info("Inside BahmniFormPrivilegesServiceImpl -->saveFormPrivileges " + privilegeItr);
            while (privilegeItr.hasNext()) {
                LinkedHashMap temp = (LinkedHashMap) privilegeItr.next();
                List<Map.Entry> entrySetList = new ArrayList<Map.Entry>(temp.entrySet());
                BahmniFormPrivilege tempPrivilege = new BahmniFormPrivilege();
                for (Map.Entry tempEntrySet : entrySetList) {
                    if (tempEntrySet.getKey().toString().equalsIgnoreCase("formId")) {
                        tempPrivilege.setFormId((Integer) temp.get("formId"));
                        formId = (Integer) temp.get("formId");
                    } else if (tempEntrySet.getKey().toString().equalsIgnoreCase("editable")) {
                        tempPrivilege.setEditable((Boolean) temp.get("editable"));
                    } else if (tempEntrySet.getKey().toString().equalsIgnoreCase("privilegeName")) {
                         String privilegeName = temp.get("privilegeName").toString();
                         tempPrivilege.setPrivilegeName(privilegeName);

                    } else if (tempEntrySet.getKey().toString().equalsIgnoreCase("viewable")) {
                        tempPrivilege.setViewable((Boolean) temp.get("viewable"));
                    }
                }
                privilegesList.add(tempPrivilege);
            }
            if (privilegesList.size() == 1 && privilegesList.get(0).getPrivilegeName().equalsIgnoreCase("")) {
                deleteAllThePrivilegesFromDB(formId);
            } else {
                oldPrivilegeList = getAllPrivilegesForForm(formId);
                if ((oldPrivilegeList != null) && !(oldPrivilegeList.isEmpty())) {
                    for (int i = 0; i < oldPrivilegeList.size(); i++) {
                        bahmniFormPrivilegeDao.deleteFormPrivilege(oldPrivilegeList.get(i));
                    }
                }
                    if (privilegesList != null) {
                        for(int i=0;i<privilegesList.size();i++){
                            resultList.add(saveFormPrivilege(privilegesList.get(i)));
                        }
                    }
            }
        return resultList;

    }
        @Transactional(readOnly = true)
    public BahmniFormPrivilege getFormPrivilege(String privilgeName , Integer formId) throws APIException {
       return bahmniFormPrivilegeDao.getFormPrivilege(privilgeName,formId);
   }
    public void deleteAllThePrivilegesFromDB( Integer formId){
        List<BahmniFormPrivilege> privilegeListToBeDeleted = getAllPrivilegesForForm(formId);
        if ((privilegeListToBeDeleted != null) && !(privilegeListToBeDeleted.isEmpty())) {
            for (int i = 0; i < privilegeListToBeDeleted.size(); i++) {
                bahmniFormPrivilegeDao.deleteFormPrivilege(privilegeListToBeDeleted.get(i));
            }
        }

    }
    @Override
    public List<BahmniFormPrivilege> getAllPrivilegesForForm(Integer formId) {
        List<BahmniFormPrivilege> formPrivileges = bahmniFormPrivilegeDao.getAllPrivilegesForForm(formId);
        return formPrivileges;

    }
    @Override
    public List<BahmniFormPrivilege>deleteAllPrivilegesForGivenFormId(Integer formId) {
        List<BahmniFormPrivilege> formPrivileges = bahmniFormPrivilegeDao.getAllPrivilegesForForm(formId);
        for(int i = 0;i<formPrivileges.size();i++){
            bahmniFormPrivilegeDao.deleteFormPrivilege(formPrivileges.get(i));
        }
        return formPrivileges;
    }


}
