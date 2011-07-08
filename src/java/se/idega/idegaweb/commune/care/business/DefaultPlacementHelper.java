/*
 * $Id: DefaultPlacementHelper.java,v 1.5 2005/03/09 20:24:26 laddi Exp $
 * Created on 5.10.2004
 * 
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 * 
 * This software is the proprietary information of Idega hf. Use is subject to
 * license terms.
 */
package se.idega.idegaweb.commune.care.business;

import java.util.Date;

import se.idega.idegaweb.commune.care.data.ChildCareApplication;
import se.idega.idegaweb.commune.care.data.ChildCareContract;

import com.idega.block.school.data.SchoolClassMember;
import com.idega.block.school.data.SchoolClassMemberLog;
import com.idega.idegaweb.IWResourceMessage;
import com.idega.util.IWTimestamp;
import com.idega.util.TimePeriod;

/**
 * 
 * Last modified: $Date: 2005/03/09 20:24:26 $ by $Author: laddi $
 * 
 * @author <a href="mailto:aron@idega.com">aron </a>
 * @version $Revision: 1.5 $
 */
public class DefaultPlacementHelper implements PlacementHelper {

	private ChildCareApplication application;

	private ChildCareContract contract;

	private SchoolClassMember member;
	
	private SchoolClassMemberLog iLog;

	/**
	 * 
	 */
	public DefaultPlacementHelper() {

	}

	public ChildCareApplication getApplication() {
		return application;
	}

	public ChildCareContract getContract() {
		return contract;
	}

	public String getCurrentCareTimeHours() {
		if (contract != null)
			return contract.getCareTime();
		return null;
	}

	public Integer getCurrentClassID() {
		if (member != null)
			return new Integer(member.getSchoolClassId());
		return null;
	}

	public Integer getCurrentSchoolTypeID() {
		if (member != null)
			return new Integer(member.getSchoolTypeId());
		return null;
	}

	public Integer getCurrentEmploymentID() {
		if (contract != null)
			return new Integer(contract.getEmploymentTypeId());
		return null;
	}

	public Integer getCurrentProviderID() {
		if (contract != null)
			return new Integer(application.getProviderId());
		return null;
	}

	public Date getEarliestPlacementDate() {
		if (contract != null) {
			IWTimestamp contractDate = new IWTimestamp(contract.getValidFromDate());
			if (iLog != null) {
				IWTimestamp logDate = new IWTimestamp(iLog.getStartDate());
				if (logDate.isLaterThan(contractDate)) {
					logDate.addDays(1);
					return logDate.getDate();
				}
			}
			contractDate.addDays(1);
			return contractDate.getDate();
		}
		else {
			return application.getFromDate();
		}
	}

	public Date getLatestPlacementDate() {
		return null;
	}

	public Integer getMaximumCareTimeHours() {
		return new Integer(99);
	}

	public boolean hasEarliestPlacementDate() {
		return true;
	}

	public boolean hasLatestPlacementDate() {
		return false;
	}

	public void setApplication(ChildCareApplication app) {
		this.application = app;
	}

	public void setContract(ChildCareContract contract) {
		this.contract = contract;
		if (contract != null)
			this.member = contract.getSchoolClassMember();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.idega.idegaweb.commune.childcare.business.PlacementHelper#getEarliestPlacementMessage()
	 */
	public IWResourceMessage getEarliestPlacementMessage() {
		return new IWResourceMessage("child_care.date_too_early", "Chosen date is too early");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.idega.idegaweb.commune.childcare.business.PlacementHelper#getLatestPlacementMessage()
	 */
	public IWResourceMessage getLatestPlacementMessage() {
		return new IWResourceMessage("child_care.date_too_late", "Chosen date is too late");
	}

	public IWResourceMessage getMessageWhenDeadlinePassed() {
		return new IWResourceMessage("child_care.deadline_has_passed", "Deadline has passed");
	}

	public boolean hasDeadlinePassed() {
		return false;
	}

	public TimePeriod getValidPeriod() {
		return null;
	}

	public SchoolClassMemberLog getPlacementLog() {
		return iLog;
	}

	public void setPlacementLog(SchoolClassMemberLog log) {
		iLog = log;
	}
}