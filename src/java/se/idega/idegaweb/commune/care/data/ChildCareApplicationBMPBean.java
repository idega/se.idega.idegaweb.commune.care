/*
 * $Id: ChildCareApplicationBMPBean.java,v 1.22.2.4 2006/04/04 14:37:53 dainis Exp $
 *
 * Copyright (C) 2002 Idega hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 *
 */
package se.idega.idegaweb.commune.care.data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

import javax.ejb.FinderException;

import se.idega.idegaweb.commune.care.business.CareConstants;
import se.idega.idegaweb.commune.care.check.data.GrantedCheck;

import com.idega.block.contract.data.Contract;
import com.idega.block.process.data.AbstractCaseBMPBean;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseStatus;
import com.idega.block.school.data.School;
import com.idega.core.file.data.ICFile;
import com.idega.data.IDOAddRelationshipException;
import com.idega.data.IDOException;
import com.idega.data.IDOQuery;
import com.idega.data.IDORemoveRelationshipException;
import com.idega.data.query.InCriteria;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;

/**
 * This class does something very clever.....
 *
 * @author palli
 * @version 1.0
 */
public class ChildCareApplicationBMPBean extends AbstractCaseBMPBean implements ChildCareApplication, Case {

	private static final long serialVersionUID = -814987565087521134L;

	public final static String ENTITY_NAME = "comm_childcare";
	private final static String CASE_CODE_KEY_DESC = "Application for child care";

	public final static String MEMBER = "ic_user_id";
	public final static String CLASSMEMBER = "SCH_CLASS_MEMBER_ID";
	public final static String SCHOOLCLASS = "sch_school_class_id";
	public final static String REGISTER_DATE = "register_date";

	public final static String PROVIDER_ID = "provider_id";
	public final static String FROM_DATE = "from_date";
	public final static String CHILD_ID = "child_id";
	public final static String QUEUE_DATE = "queue_date";
	public final static String METHOD = "method";
	public final static String CARE_TIME = "care_time_string";
	public final static String CARE_TIME_OLD = "care_time";
	public final static String CHOICE_NUMBER = "choice_number";
	public final static String CHECK_ID = "check_id";
	public final static String CONTRACT_ID = "contract_id";
	public final static String CONTRACT_FILE_ID = "contract_file_id";
	public final static String OFFER_VALID_UNTIL = "offer_valid_until";
	public final static String REJECTION_DATE = "rejection_date";
	public final static String PROGNOSIS = "prognosis";
	public final static String PRESENTATION = "presentation";
	public final static String CC_MESSAGE = "cc_message";
	public final static String QUEUE_ORDER = "queue_order";
	public final static String APPLICATION_STATUS = "application_status";
	public final static String HAS_PRIORITY = "has_priority";
	public final static String HAS_DATE_SET = "has_date_set";
	public final static String HAS_QUEUE_PRIORITY = "has_queue_priority";
	public final static String PRESCHOOL = "preschool";
	public final static String LAST_REPLY_DATE = "last_reply_date";
	public final static String REQUESTED_CANCEL_DATE = "requested_cancel_date";
	public final static String CANCEL_MESSAGE = "cancel_message";
	public final static String CANCEL_PARENTAL_LEAVE = "cancel_parental_leave";
	public final static String CANCEL_FORM_FILE_ID = "cancel_form_file_id";
	public final static String CANCEL_CONFIRMATION_RECEIVED = "cancel_confirmation_received";

	protected final static String EXTRA_CONTRACT = "extra_contract";
	protected final static String EXTRA_CONTRACT_MESSAGE = "extra_contract_message";
	protected final static String EXTRA_CONTRACT_OTHER = "extra_contract_other";
	protected final static String EXTRA_CONTRACT_OTHER_MESSAGE = "extra_contract_message_other";

    protected final static String FROM_DATE_REQUESTED = "from_date_requested"; // Requested start date
    protected final static String CANCEL_REQUEST_RECEIVED = "cancel_request_received"; // The date the parents want as the last day of placement
    protected final static String CANCEL_DATE_REQUESTED = "cancel_date_requested"; // The date the parent registered the cancellation

  protected final static String FROM_TIME = "from_time";
  protected final static String TO_TIME = "to_time";
  protected final static String FEE = "fee";

	protected final int SORT_DATE_OF_BIRTH = 1;
	protected final int SORT_QUEUE_DATE = 2;
	protected final int SORT_PLACEMENT_DATE = 3;

    public final int ORDER_BY_QUEUE_DATE = 1;
    public final int ORDER_BY_DATE_OF_BIRTH = 2;

	/**
	 * @see com.idega.block.process.data.AbstractCaseBMPBean#getCaseCodeKey()
	 */
	@Override
	public String getCaseCodeKey() {
		return CareConstants.CASE_CODE_KEY;
	}

	/**
	 * @see com.idega.block.process.data.AbstractCaseBMPBean#getCaseCodeDescription()
	 */
	@Override
	public String getCaseCodeDescription() {
		return CASE_CODE_KEY_DESC;
	}

	/**
	 * @see com.idega.data.IDOLegacyEntity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/**
	 * @see com.idega.data.IDOLegacyEntity#initializeAttributes()
	 */
	@Override
	public void initializeAttributes() {
		addAttribute(getIDColumnName());
		addAttribute(FROM_DATE,"",true,true,java.sql.Date.class);
		addAttribute(QUEUE_DATE,"",true,true,java.sql.Date.class);
		addAttribute(METHOD,"",true,true,java.lang.Integer.class);
		addAttribute(CARE_TIME_OLD,"",true,true,java.lang.Integer.class);
		addAttribute(CARE_TIME,"",true,true,java.lang.String.class);
		addAttribute(CHOICE_NUMBER,"",true,true,java.lang.Integer.class);
		addAttribute(REJECTION_DATE,"",true,true,java.sql.Date.class);
		addAttribute(OFFER_VALID_UNTIL,"",true,true,java.sql.Date.class);
		addAttribute(PROGNOSIS,"",true,true,java.lang.String.class,1000);
		addAttribute(PRESENTATION,"",true,true,java.lang.String.class,1000);
		addAttribute(CC_MESSAGE,"",true,true,java.lang.String.class,1000);
		addAttribute(QUEUE_ORDER,"",true,true,java.lang.Integer.class);
		addAttribute(APPLICATION_STATUS,"",true,true,java.lang.String.class,1);
		addAttribute(HAS_PRIORITY,"",true,true,java.lang.Boolean.class);
		addAttribute(HAS_DATE_SET,"",true,true,java.lang.Boolean.class);
		addAttribute(HAS_QUEUE_PRIORITY,"",true,true,java.lang.Boolean.class);
		addAttribute(PRESCHOOL,"",true,true,java.lang.String.class);
		addAttribute(LAST_REPLY_DATE,"",true,true,java.sql.Date.class);

		addAttribute(EXTRA_CONTRACT,"",true,true,java.lang.Boolean.class);
		addAttribute(EXTRA_CONTRACT_MESSAGE,"",true,true,java.lang.String.class);
		addAttribute(EXTRA_CONTRACT_OTHER,"",true,true,java.lang.Boolean.class);
		addAttribute(EXTRA_CONTRACT_OTHER_MESSAGE,"",true,true,java.lang.String.class);

		addManyToOneRelationship(PROVIDER_ID,School.class);
		addManyToOneRelationship(CHILD_ID,User.class);
		addManyToOneRelationship(CHECK_ID,GrantedCheck.class);
		addManyToOneRelationship(CONTRACT_ID,Contract.class);
		addManyToOneRelationship(CONTRACT_FILE_ID,ICFile.class);

		addAttribute(REQUESTED_CANCEL_DATE, "Requested cancel date", Date.class);
		addAttribute(CANCEL_MESSAGE, "Cancel message", String.class, 255);
		addAttribute(CANCEL_PARENTAL_LEAVE, "Cancel reason", Boolean.class);
		addAttribute(CANCEL_CONFIRMATION_RECEIVED, "Cancel confirmation received", Date.class);
		addManyToOneRelationship(CANCEL_FORM_FILE_ID,ICFile.class);

        addAttribute(FROM_DATE_REQUESTED, "", true, true, java.sql.Date.class);
        addAttribute(CANCEL_REQUEST_RECEIVED, "", true, true, java.sql.Date.class);
        addAttribute(CANCEL_DATE_REQUESTED, "", true, true, java.sql.Date.class);

    addAttribute(FROM_TIME, "From time", Timestamp.class);
    addAttribute(TO_TIME, "To time", Timestamp.class);
    addAttribute(FEE, "Fee", Float.class);
	}

	public float getFee() {
		return getFloatColumnValue(FEE, 0);
	}

	public void setFee(float fee) {
		setColumn(FEE, fee);
	}

	public Time getFromTime() {
		Timestamp stamp = getTimestampColumnValue(FROM_TIME);
		if (stamp != null) {
			return new IWTimestamp(stamp).getTime();
		}
		return null;
	}

	public void setFromTime(Timestamp stamp) {
		setColumn(FROM_TIME, stamp);
	}

	public Time getToTime() {
		Timestamp stamp = getTimestampColumnValue(TO_TIME);
		if (stamp != null) {
			return new IWTimestamp(stamp).getTime();
		}
		return null;
	}

	public void setToTime(Timestamp stamp) {
		setColumn(TO_TIME, stamp);
	}

	public int getProviderId() {
		return getIntColumnValue(PROVIDER_ID);
	}

	public School getProvider() {
		return (School)getColumnValue(PROVIDER_ID);
	}

	public Date getFromDate() {
		return (Date)getColumnValue(FROM_DATE);
	}

	public int getChildId() {
		return getIntColumnValue(CHILD_ID);
	}

	public User getChild() {
		return (User) getColumnValue(CHILD_ID);
	}

	public Date getQueueDate() {
		return (Date)getColumnValue(QUEUE_DATE);
	}

	public int getMethod() {
		return getIntColumnValue(METHOD);
	}

	public String getCareTime() {
		String careTime = getStringColumnValue(CARE_TIME);
		if (careTime == null) {
			int oldCareTime = getIntColumnValue(CARE_TIME_OLD);
			if (oldCareTime != -1) {
				careTime = String.valueOf(oldCareTime);
			}
		}
		return careTime;
	}

	public int getChoiceNumber() {
		return getIntColumnValue(CHOICE_NUMBER);
	}

	public int getCheckId() {
		return getIntColumnValue(CHECK_ID);
	}

	public GrantedCheck getCheck() {
		return (GrantedCheck)getColumnValue(CHECK_ID);
	}

	public Date getRejectionDate() {
		return (Date)getColumnValue(REJECTION_DATE);
	}

	public Date getOfferValidUntil() {
		return (Date)getColumnValue(OFFER_VALID_UNTIL);
	}

	public Date getLastReplyDate() {
		return (Date)getColumnValue(LAST_REPLY_DATE);
	}

	public int getContractId() {
		return getIntColumnValue(CONTRACT_ID);
	}

	public Contract getContract() {
		return (Contract)getColumnValue(CONTRACT_ID);
	}

	public int getContractFileId() {
		return getIntColumnValue(CONTRACT_FILE_ID);
	}

	public ICFile getContractFile() {
		return (ICFile) getColumnValue(CONTRACT_FILE_ID);
	}

	public String getPrognosis() {
		return getStringColumnValue(PROGNOSIS);
	}

	public String getPresentation() {
		return getStringColumnValue(PRESENTATION);
	}

	public String getPreSchool() {
		return getStringColumnValue(PRESCHOOL);
	}

	public String getMessage() {
		return getStringColumnValue(CC_MESSAGE);
	}

	public int getQueueOrder() {
		return getIntColumnValue(QUEUE_ORDER);
	}

	public char getApplicationStatus() {
		String status = this.getStringColumnValue(APPLICATION_STATUS);
		if (status != null)
			return status.charAt(0);
		else
			return 'A';
	}



	public boolean getHasPriority() {
		return getBooleanColumnValue(HAS_PRIORITY, false);
	}

	public boolean getHasDateSet() {
		return getBooleanColumnValue(HAS_DATE_SET, false);
	}

	public boolean getHasQueuePriority() {
		return getBooleanColumnValue(HAS_QUEUE_PRIORITY, false);
	}

	public boolean getHasExtraContract() {
		return getBooleanColumnValue(EXTRA_CONTRACT, false);
	}

	public String getExtraContractMessage() {
		return getStringColumnValue(EXTRA_CONTRACT_MESSAGE);
	}

	public boolean getHasExtraContractOther() {
		return getBooleanColumnValue(EXTRA_CONTRACT_OTHER, false);
	}

	public String getExtraContractMessageOther() {
		return getStringColumnValue(EXTRA_CONTRACT_OTHER_MESSAGE);
	}

	public Date getRequestedCancelDate() {
		return getDateColumnValue(REQUESTED_CANCEL_DATE);
	}

	public String getCancelMessage() {
		return getStringColumnValue(CANCEL_MESSAGE);
	}

	public boolean getParentalLeave() {
		return getBooleanColumnValue(CANCEL_PARENTAL_LEAVE, false);
	}

	public ICFile getCancelFormFile() {
		return (ICFile) getColumnValue(CANCEL_FORM_FILE_ID);
	}

	public int getCancelFormFileID() {
		return getIntColumnValue(CANCEL_FORM_FILE_ID);
	}

	public Date getCancelConfirmationReceived() {
		return getDateColumnValue(CANCEL_CONFIRMATION_RECEIVED);
	}

    public Date getFromDateRequested(){
        return getDateColumnValue(FROM_DATE_REQUESTED);
    }

    public Date getCancelRequestReceived(){
        return getDateColumnValue(CANCEL_REQUEST_RECEIVED);
    }

    public Date getCancelDateRequested(){
        return getDateColumnValue(CANCEL_DATE_REQUESTED);
    }

	public void setProviderId(int id) {
		setColumn(PROVIDER_ID,id);
	}

	public void setProvider(School provider) {
		setColumn(PROVIDER_ID,provider);
	}

	public void setFromDate(Date date) {
	    if(date!=null)
	        setColumn(FROM_DATE,date);
	}

	public void setChildId(int id) {
		setColumn(CHILD_ID,id);
	}

	public void setChild(User child) {
		setColumn(CHILD_ID,child);
	}

	public void setQueueDate(Date date) {
		setColumn(QUEUE_DATE,date);
	}

	public void setMethod(int method) {
		setColumn(METHOD,method);
	}

	public void setCareTime(String careTime) {
		setColumn(CARE_TIME,careTime);
	}

	public void setChoiceNumber(int number) {
		setColumn(CHOICE_NUMBER,number);
	}

	public void setCheckId(int checkId) {
		setColumn(CHECK_ID,checkId);
	}

	public void setCheck(GrantedCheck check) {
		setColumn(CHECK_ID,check);
	}

	public void setRejectionDate(Date date) {
		setColumn(REJECTION_DATE,date);
	}

	public void setOfferValidUntil(Date date) {
		setColumn(OFFER_VALID_UNTIL,date);
	}

	public void setLastReplyDate(Date date) {
		setColumn(LAST_REPLY_DATE,date);
	}

	public void setContractId(int id) {
		setColumn(CONTRACT_ID,id);
	}

	public void setContractId(Integer id) {
		setColumn(CONTRACT_ID,id);
	}

	public void setContractFileId(int id) {
		setColumn(CONTRACT_FILE_ID,id);
	}

	public void setContractFileId(Integer id) {
		setColumn(CONTRACT_FILE_ID,id);
	}

	public void setContractFile(ICFile contractFile) {
		setColumn(CONTRACT_FILE_ID, contractFile);
	}

	public void setPrognosis(String prognosis) {
		setColumn(PROGNOSIS,prognosis);
	}

	public void setPresentation(String presentation) {
		setColumn(PRESENTATION,presentation);
	}

	public void setPreSchool(java.lang.String preSchool){
		setColumn(PRESCHOOL, preSchool);
	}

	public void setMessage(String message) {
		setColumn(CC_MESSAGE,message);
	}

	public void setQueueOrder(int order) {
		setColumn(QUEUE_ORDER,order);
	}

	public void setApplicationStatus(char status) {
		setColumn(APPLICATION_STATUS,String.valueOf(status));
	}

	public void setHasPriority(boolean hasPriority) {
		setColumn(HAS_PRIORITY, hasPriority);
	}

	public void setHasDateSet(boolean hasDateSet) {
		setColumn(HAS_DATE_SET, hasDateSet);
	}

	public void setHasQueuePriority(boolean hasPriority) {
		setColumn(HAS_QUEUE_PRIORITY, hasPriority);
	}

	public void setRejectionDateAsNull(boolean setAsNull) {
		if (setAsNull)
			removeFromColumn(ENTITY_NAME);
	}

	public void setHasExtraContract(boolean hasExtraContract) {
		setColumn(EXTRA_CONTRACT, hasExtraContract);
	}

	public void setExtraContractMessage(String message) {
		setColumn(EXTRA_CONTRACT_MESSAGE, message);
	}

	public void setHasExtraContractOther(boolean hasExtraContractOther) {
		setColumn(EXTRA_CONTRACT_OTHER, hasExtraContractOther);
	}

	public void setExtraContractMessageOther(String message) {
		setColumn(EXTRA_CONTRACT_OTHER_MESSAGE, message);
	}

	public void setRequestedCancelDate(Date date) {
		setColumn(REQUESTED_CANCEL_DATE, date);
	}

	public void setCancelMessage(String message) {
		setColumn(CANCEL_MESSAGE, message);
	}

	public void setParentalLeave(boolean parentalLeave) {
		setColumn(CANCEL_PARENTAL_LEAVE, parentalLeave);
	}

	public void setCancelFormFile(ICFile file) {
		setColumn(CANCEL_FORM_FILE_ID, file);
	}

	public void setCancelFormFileID(int fileID) {
		setColumn(CANCEL_FORM_FILE_ID, fileID);
	}

	public void setCancelConfirmationReceived(Date date) {
		setColumn(CANCEL_CONFIRMATION_RECEIVED, date);
	}

    public void setFromDateRequested(Date date) {
        setColumn(FROM_DATE_REQUESTED, date);
    }

    public void setCancelRequestReceived(Date date) {
        setColumn(CANCEL_REQUEST_RECEIVED, date);
    }

    public void setCancelDateRequested(Date date) {
        setColumn(CANCEL_DATE_REQUESTED, date);
    }

	public Collection ejbFindAll() throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this);

		return idoFindPKsBySQL(sql.toString());
  }

	public Collection ejbFindAllCasesByProviderAndStatus(int providerId, CaseStatus caseStatus) throws FinderException {
		return ejbFindAllCasesByProviderStatus(providerId, caseStatus.getStatus());
	}

	public Collection ejbFindAllCasesByProviderAndStatus(School provider, String caseStatus) throws FinderException {
		return ejbFindAllCasesByProviderStatus(((Integer)provider.getPrimaryKey()).intValue(), caseStatus);
	}

	public Collection ejbFindAllCasesByProviderAndStatus(School provider, CaseStatus caseStatus) throws FinderException {
		return ejbFindAllCasesByProviderStatus(((Integer)provider.getPrimaryKey()).intValue(), caseStatus.getStatus());
	}

	public Collection ejbFindAllCasesByProviderStatus(int providerId, String caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerId);
		sql.appendAnd().appendEqualsQuoted("p.case_status",caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);
		sql.appendOrderBy("c."+QUEUE_DATE+",c."+QUEUE_ORDER);

		return idoFindPKsBySQL(sql.toString());
	}


	public Collection ejbFindAllChildCasesByProvider(int providerId) throws FinderException {

		StringBuffer sql = new StringBuffer(
            "select m.* from msg_letter_message m, proc_case p, comm_childcare c" +
            " where m.msg_letter_message_id = p.proc_case_id and " +
            " c.provider_id = " + providerId + " and " +
            " p.parent_case_id in (select proc_case_id from proc_case where p.proc_case_id = c.comm_childcare_id)");

		return idoFindPKsBySQL(sql.toString());
	}





	public Collection ejbFindAllCasesByProviderAndStatus(int providerId, String caseStatus, int numberOfEntries, int startingEntry) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerId);
		sql.appendAnd().appendEqualsQuoted("p.case_status",caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);
		sql.appendOrderBy("c."+QUEUE_ORDER);

		return idoFindPKsBySQL(sql.toString(), numberOfEntries, startingEntry);
	}

    public Collection ejbFindAllCasesByProviderAndNotInStatus(int providerId, String[] caseStatus, int numberOfEntries, int startingEntry) throws FinderException {
        return ejbFindAllCasesByProviderAndNotInStatus(providerId, caseStatus, numberOfEntries, startingEntry, ORDER_BY_QUEUE_DATE);
    }

    public Collection ejbFindAllCasesByProviderAndNotInStatus(int providerId, String[] caseStatus, int numberOfEntries, int startingEntry, int orderBy) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");

        if ( orderBy == ORDER_BY_DATE_OF_BIRTH ) {
            sql.append(", ic_user u");
            sql.appendWhereEquals("c."+CHILD_ID, "u.ic_user_id");

            sql.appendAndEquals("c."+getIDColumnName(), "p.proc_case_id");
        } else {
            sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
        }

		sql.appendAndEquals("c."+PROVIDER_ID,providerId);
		if (caseStatus != null)
			sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);

        String s = null;
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            s = "u.date_of_birth asc,";
        } else {
            s = "c." + QUEUE_DATE + ",";
        }
        sql.appendOrderBy("c." + APPLICATION_STATUS + " desc, " + s + " c." + QUEUE_ORDER);

		return idoFindPKsBySQL(sql.toString(), numberOfEntries, startingEntry);
	}

    public Collection ejbFindAllCasesByProviderAndNotInStatus(int providerId, int sortBy, Date fromDate, Date toDate, String[] caseStatus, int numberOfEntries, int startingEntry) throws FinderException {
        return ejbFindAllCasesByProviderAndNotInStatus(providerId, sortBy, fromDate, toDate, caseStatus, numberOfEntries, startingEntry, ORDER_BY_QUEUE_DATE);
    }

	public Collection ejbFindAllCasesByProviderAndNotInStatus(int providerId, int sortBy, Date fromDate, Date toDate, String[] caseStatus, int numberOfEntries, int startingEntry, int orderBy) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		if ( (sortBy == SORT_DATE_OF_BIRTH)||(orderBy == ORDER_BY_DATE_OF_BIRTH) )
			sql.append(", ic_user u");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerId);
		if (caseStatus != null)
			sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);

        if ( (sortBy == SORT_DATE_OF_BIRTH)||(orderBy == ORDER_BY_DATE_OF_BIRTH) )
            sql.appendAndEquals("c."+CHILD_ID, "u.ic_user_id");
		if (sortBy == SORT_DATE_OF_BIRTH) {
			sql.appendAnd().append("u.date_of_birth").appendGreaterThanOrEqualsSign().append(fromDate);
			sql.appendAnd().append("u.date_of_birth").appendLessThanOrEqualsSign().append(toDate);
		}
		else if (sortBy == SORT_QUEUE_DATE) {
			sql.appendAnd().append("c."+QUEUE_DATE).appendGreaterThanOrEqualsSign().append(fromDate);
			sql.appendAnd().append("c."+QUEUE_DATE).appendLessThanOrEqualsSign().append(toDate);
		}
		else if (sortBy == SORT_PLACEMENT_DATE) {
			sql.appendAnd().append("c."+FROM_DATE).appendGreaterThanOrEqualsSign().append(fromDate);
			sql.appendAnd().append("c."+FROM_DATE).appendLessThanOrEqualsSign().append(toDate);
		}

        String s = null;
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            s = "u.date_of_birth asc,";
        } else {
            s = "c." + QUEUE_DATE + ",";
        }
		sql.appendOrderBy("c." + APPLICATION_STATUS + " desc, " + s + " c." + QUEUE_ORDER);

		return idoFindPKsBySQL(sql.toString(), numberOfEntries, startingEntry);
	}

    public Collection ejbFindAllCasesByProviderStatus(int providerId, String caseStatus[]) throws FinderException {
        return ejbFindAllCasesByProviderStatus(providerId, caseStatus,  ORDER_BY_QUEUE_DATE);
    }

    public Collection ejbFindAllCasesByProviderStatus(int providerId, String caseStatus[], int orderBy) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");

        if(orderBy == ORDER_BY_DATE_OF_BIRTH)
            sql.append(", ic_user u");

		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");

        if(orderBy == ORDER_BY_DATE_OF_BIRTH)
            sql.appendAndEquals("c."+CHILD_ID, "u.ic_user_id");

		sql.appendAndEquals("c."+PROVIDER_ID,providerId);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);

        String order;
        if(orderBy == ORDER_BY_DATE_OF_BIRTH){
            order = "u.date_of_birth";
        } else {
            order = "c. "+ QUEUE_DATE;
        }

        sql.appendOrderBy(order + ",c."+QUEUE_ORDER);

		return idoFindPKsBySQL(sql.toString());
	}

	public Collection ejbFindAllByAreaAndApplicationStatus(Object areaID, String applicationStatus[], String caseCode, Date queueDate, Date placementDate, boolean firstHandOnly) throws FinderException {
		IDOQuery inQuery = idoQuery();
		inQuery.appendSelect().append("ic_user_id").appendFrom().append("sch_class_member");

		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p, sch_school s, ic_user u");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,"s.sch_school_id");
		sql.appendAndEquals("c."+CHILD_ID,"u.ic_user_id");
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}
		sql.appendAnd().append("c."+APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatus);
		if (areaID != null) {
			sql.appendAndEquals("s.sch_school_area_id", areaID);
		}
		sql.appendAnd().append(QUEUE_DATE).appendLessThanSign().append(queueDate);
		sql.appendAnd().append(FROM_DATE).appendLessThanSign().append(placementDate);
		if (firstHandOnly) {
			sql.appendAndEquals(CHOICE_NUMBER, 1);
		}
		sql.appendAnd().append(CHILD_ID).appendNotIn(inQuery);
		sql.appendOrderBy("u.last_name, u.first_name, u.last_name, c."+APPLICATION_STATUS+", c."+QUEUE_DATE);

		return idoFindPKsBySQL(sql.toString());
	}

	public Collection ejbFindAllCasesByProviderStatusNotRejected(int providerId, String caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerId);
		sql.appendAnd().appendEqualsQuoted("p.case_status",caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);
		sql.appendAnd().append(REJECTION_DATE).append(" is null");

		return idoFindPKsBySQL(sql.toString());
	}

	@Override
	public Collection ejbFindAllCasesByUserAndStatus(User owner, String caseStatus) throws  FinderException {
		return super.ejbFindAllCasesByUserAndStatus(owner,caseStatus);
	}

	@Override
	public Collection ejbFindAllCasesByStatus(String caseStatus) throws FinderException {
		return super.ejbFindAllCasesByStatus(caseStatus);
	}

	public Collection ejbFindApplicationsByProviderAndStatus(int providerID, String caseStatus) throws FinderException {
		String[] status = { caseStatus };
		return ejbFindApplicationsByProviderAndStatus(providerID, status, -1, -1);
	}

	public Collection ejbFindApplicationsByProviderAndStatus(int providerID, String[] caseStatus) throws FinderException {
		return ejbFindApplicationsByProviderAndStatus(providerID, caseStatus, -1, -1);
	}

	public Collection ejbFindApplicationsByProviderAndStatus(int providerID, String[] caseStatus, String caseCode) throws FinderException {
		return ejbFindApplicationsByProviderAndStatus(providerID, caseStatus, caseCode, -1, -1);
	}


	public Collection ejbFindApplicationsByProviderAndStatus(int providerID, String caseStatus, int numberOfEntries, int startingEntry) throws FinderException {
		String[] status = { caseStatus };
		return ejbFindApplicationsByProviderAndStatus(providerID, status, numberOfEntries, startingEntry);
	}

	public Collection ejbFindApplicationsByProviderAndStatus(int providerID, String[] caseStatus, int numberOfEntries, int startingEntry) throws FinderException {
		return ejbFindApplicationsByProviderAndStatus(providerID, caseStatus, null, numberOfEntries, startingEntry);
	}


	public Collection ejbFindApplicationsByProviderAndStatus(int providerID, String[] caseStatus, String caseCode, int numberOfEntries, int startingEntry) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p, ic_user u");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID, "u.ic_user_id");
		sql.appendAndEquals("c."+PROVIDER_ID, providerID);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		if (caseCode != null)
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		sql.appendOrderBy("u.last_name, u.first_name, u.middle_name");

		if (numberOfEntries == -1)
			return idoFindPKsBySQL(sql.toString());
		else
			return idoFindPKsBySQL(sql.toString(), numberOfEntries, startingEntry);
	}


	public Collection ejbFindApplicationsByProviderAndStatus(Integer providerID, String[] applicationStatus, Date fromDateOfBirth, Date toDateOfBirth, Date fromDate, Date toDate) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, ic_user u");
		sql.appendWhereEquals("c."+CHILD_ID, "u.ic_user_id");
		if (providerID != null) {
			sql.appendAndEquals("c."+PROVIDER_ID, providerID);
		}
		if (fromDateOfBirth != null) {
			sql.appendAnd().append("u.date_of_birth").appendGreaterThanOrEqualsSign().append(fromDateOfBirth);
		}
		if (toDateOfBirth != null) {
			sql.appendAnd().append("u.date_of_birth").appendLessThanOrEqualsSign().append(toDateOfBirth);
		}
		if (fromDate != null) {
			sql.appendAnd().append("c."+REJECTION_DATE).appendGreaterThanOrEqualsSign().append(fromDate);
		}
		if (toDate != null) {
			sql.appendAnd().append("c."+REJECTION_DATE).appendLessThanOrEqualsSign().append(toDate);
		}
		sql.appendAnd().append("c."+APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatus);
		sql.appendOrderBy("u.last_name, u.first_name, u.middle_name");

		return idoFindPKsBySQL(sql.toString());
	}

	public Collection ejbFindApplicationsByProviderAndApplicationStatusAndTerminatedDate(int providerID, String[] applicationStatuses, Date terminatedDate) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p, ic_user u");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID, "u.ic_user_id");
		sql.appendAndEquals("c."+PROVIDER_ID, providerID);
		sql.appendAnd().append("c."+APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatuses);
		sql.appendAnd().append("c."+REJECTION_DATE).appendLessThanOrEqualsSign().append(terminatedDate);
		sql.appendOrderBy("u.last_name, u.first_name, u.middle_name");

		return idoFindPKsBySQL(sql.toString());
	}

	public Collection ejbFindApplicationsByProviderAndApplicationStatus(int providerID, String[] applicationStatuses) throws FinderException {
		return ejbFindApplicationsByProviderAndApplicationStatus(providerID, applicationStatuses, null);
	}

	public Collection ejbFindApplicationsByProviderAndApplicationStatus(int providerID, String[] applicationStatuses, String caseCode) throws FinderException {
		return ejbFindApplicationsByProviderAndApplicationStatus(providerID, applicationStatuses, caseCode, -1, -1);
	}

	public Collection ejbFindApplicationsByProviderAndApplicationStatus(int providerID, String[] applicationStatuses, String caseCode, int numberOfEntries, int startingEntry) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p, ic_user u");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID, "u.ic_user_id");
		sql.appendAndEquals("c."+PROVIDER_ID, providerID);
		sql.appendAnd().append("c."+APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatuses);
		if (caseCode != null)
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		sql.appendOrderBy("u.last_name, u.first_name, u.middle_name");

		if (numberOfEntries == -1)
			return idoFindPKsBySQL(sql.toString());
		else
			return idoFindPKsBySQL(sql.toString(), numberOfEntries, startingEntry);
	}

	public Integer ejbFindApplicationByChildAndApplicationStatus(int childID, String[] applicationStatuses) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID, childID);
		sql.appendAnd().append("c."+APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatuses);

		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Collection ejbFindApplicationsByChildAndApplicationStatus(int childID, String[] applicationStatuses) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this);
		sql.appendWhereEquals(CHILD_ID, childID);
		sql.appendAnd().append(APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatuses);

		return idoFindPKsByQuery(sql);
	}

	public Collection ejbFindApplicationsWithoutPlacing() throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelect().append(" distinct c.* ").appendFrom().append(getEntityName()).append(" c, comm_childcare_archive a");
		sql.appendWhereEquals("c."+getIDColumnName(), "a.application_id").appendAnd().append("c." + APPLICATION_STATUS).appendNOTEqual().appendWithinSingleQuotes("E");
		sql.appendAnd().append("a.sch_class_member_id").appendIsNull();

		return idoFindPKsByQuery(sql);
	}

	public Integer ejbFindApplicationByChildAndChoiceNumber(User child, int choiceNumber) throws FinderException {
		return ejbFindApplicationByChildAndChoiceNumber(((Integer)child.getPrimaryKey()).intValue(), choiceNumber);
	}

	public Integer ejbFindApplicationByChildAndChoiceNumber(int childID, int choiceNumber) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(CHOICE_NUMBER, choiceNumber).appendAndEquals(CHILD_ID,childID);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindApplicationByChildAndChoiceNumberWithStatus(int childID, int choiceNumber, String caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.append("select c.* from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID, childID);
		sql.appendAnd().appendEqualsQuoted("p.case_status", caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code", CASE_CODE_KEY);
		sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindApplicationByChildAndChoiceNumberInStatus(int childID, int choiceNumber, String[] caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID, childID);
		//sql.appendAnd().appendEqualsQuoted("p.case_code", CASE_CODE_KEY);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindApplicationByChildAndChoiceNumberNotInStatus(int childID, int choiceNumber, String[] caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID, childID);
		//sql.appendAnd().appendEqualsQuoted("p.case_code", CASE_CODE_KEY);
		sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Collection ejbFindApplicationByChild(int childID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(CHILD_ID,childID);
		sql.appendOrderBy(CHOICE_NUMBER);
		return super.idoFindPKsByQuery(sql);
	}

	public Integer ejbFindApplicationByChildAndProvider(int childID, int providerID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(CHILD_ID,childID);
		sql.appendAndEquals(PROVIDER_ID, providerID);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindApplicationByChildAndProviderAndStatus(int childID, int providerID, String[] status) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(CHILD_ID,childID);
		sql.appendAndEquals(PROVIDER_ID, providerID);
		sql.appendAnd().append(APPLICATION_STATUS).appendInArrayWithSingleQuotes(status);
		sql.appendOrderByDescending(FROM_DATE);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindNewestApplication(int providerID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAnd().append(QUEUE_DATE).appendLessThanSign().append(date);
		sql.appendOrderBy(QUEUE_DATE+" desc, "+QUEUE_ORDER+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindOldestApplication(int providerID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAnd().append(QUEUE_DATE).appendGreaterThanSign().append(date);
		sql.appendOrderBy(QUEUE_DATE+", "+QUEUE_ORDER);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Collection ejbFindApplicationByChildAndNotInStatus(int childID, String[] caseStatus) throws FinderException {
		return ejbFindApplicationByChildAndNotInStatus(childID, caseStatus, null);
	}

	public Collection ejbFindApplicationByChildAndNotInStatus(int childID, String[] caseStatus, String caseCode) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}
		sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		sql.appendOrderBy(CHOICE_NUMBER);
		return super.idoFindPKsByQuery(sql);
	}

	public Collection ejbFindApplicationByChildAndInStatus(int childID, String[] caseStatus, String caseCode) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendOrderBy(CHOICE_NUMBER);
		return super.idoFindPKsByQuery(sql);
	}

	public Integer ejbFindActiveApplicationByChild(int childID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);
		sql.appendAnd().appendEqualsQuoted("p.case_status", "KLAR");
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindActiveApplicationByChildAndStatus(int childID, String[] caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendOrderBy(CHOICE_NUMBER);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindActiveApplicationByChildAndStatusAndCaseCode(int childID, String[] caseStatus, String caseCode) throws FinderException {
		String[] caseCodes = {caseCode};
		return ejbFindActiveApplicationByChildAndStatusAndCaseCodes(childID, caseStatus, caseCodes);
	}

	public Integer ejbFindActiveApplicationByChildAndStatusAndCaseCodes(int childID, String[] caseStatus, String[] caseCodes) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		sql.appendAnd().append("p.case_code").appendInArrayWithSingleQuotes(caseCodes);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendOrderBy(CHOICE_NUMBER);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public int ejbHomeGetNumberOfActiveApplications(int childID) throws IDOException {
		return ejbHomeGetNumberOfActiveApplications(childID, null);
	}

	public int ejbHomeGetNumberOfActiveApplications(int childID, String caseCode) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}
		sql.appendAnd().appendEqualsQuoted("p.case_status", "KLAR");
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplicationsByStatusAndActiveDate(int childID, String[] caseStatus, String caseCode, Date activeDate) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}
		sql.appendAnd().appendLeftParenthesis().append("c."+REJECTION_DATE).appendGreaterThanOrEqualsSign().append(activeDate);
		sql.appendOr().append("c."+REJECTION_DATE).appendIsNull().appendRightParenthesis();
		return idoGetNumberOfRecords(sql);
	}

	public Collection ejbFindApplicationsByProviderAndDate(int providerID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(PROVIDER_ID,providerID);
		sql.appendAndEquals(QUEUE_DATE, date);
		sql.appendOrderBy(QUEUE_ORDER);
		return super.idoFindPKsByQuery(sql);
	}

	public Collection ejbFindApplicationsBeforeLastReplyDate(Date date, String[] caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendAnd().append("c."+LAST_REPLY_DATE).appendLessThanSign().append(date);
		return idoFindPKsByQuery(sql);
	}

	public Collection ejbFindApplicationsByProviderAndBeforeDate(int providerID, Date date, String[] caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		if (providerID != -1) {
			sql.appendAndEquals("c."+PROVIDER_ID,providerID);
		}
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendAnd().append("c."+QUEUE_DATE).appendLessThanSign().append(date);
		return idoFindPKsByQuery(sql);
	}

	public int ejbHomeGetNumberOfApplications(int providerID, String caseStatus) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerID);
		sql.appendAnd().appendEqualsQuoted("p.case_status",caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);

		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplications(int providerID, String[] caseStatus) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerID);
		sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);

		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplicationsByStatus(int providerID, String[] caseStatus) throws IDOException {
		Table table = new Table(this,"c");
		Table caseTable = new Table(Case.class,"p");
		SelectQuery query = new SelectQuery(table);
		query.addColumn(table,CHILD_ID);
		query.setAsCountQuery(true);
		query.addJoin(table,getIDColumnName(),caseTable,"proc_case_id");
		query.addCriteria(new MatchCriteria(table,PROVIDER_ID,MatchCriteria.EQUALS,providerID));
		query.addCriteria(new InCriteria(caseTable,"case_status",caseStatus));
		return idoGetNumberOfRecords(query);
	}

	public int ejbHomeGetNumberOfApplicationsForChild(int childID) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(CHILD_ID, childID);

		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplicationsForChild(int childID, String caseStatus, String caseCode) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		sql.appendAnd().appendEqualsQuoted("p.case_status",caseStatus);
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}

		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplicationsForChildNotInStatus(int childID, String[] caseStatus) throws IDOException {
		return ejbHomeGetNumberOfApplicationsForChildNotInStatus(childID, caseStatus, null);
	}

	public int ejbHomeGetNumberOfApplicationsForChildNotInStatus(int childID, String[] caseStatus, String caseCode) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}

		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplicationsForChildInStatus(int childID, String[] caseStatus, String caseCode) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		if (caseCode != null) {
			sql.appendAnd().appendEqualsQuoted("p.case_code",caseCode);
		}

		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplicationsForChildInStatusNotWithProvider(int childID, int providerID, Date date, String[] caseStatus) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendAnd().append("c." + PROVIDER_ID).appendNOTEqual().append(providerID);
		sql.appendAnd().append("c." + FROM_DATE).appendGreaterThanSign().append(date);

		return idoGetNumberOfRecords(sql);
	}

	public Integer ejbFindFutureApplicationForChildInStatusNotWithProvider(int childID, int providerID, Date date, String[] caseStatus) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.append("select * from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+CHILD_ID,childID);
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(caseStatus);
		sql.appendAnd().append("c." + PROVIDER_ID).appendNOTEqual().append(providerID);
		sql.appendAnd().append("c." + FROM_DATE).appendGreaterThanSign().append(date);
		sql.appendOrderBy(FROM_DATE);

		return (Integer) idoFindOnePKByQuery(sql);
	}

	public int ejbHomeGetNumberOfPlacedApplications(int childID, int providerID, String[] caseStatus) throws IDOException {
	    //SelectQuery query = idoSelectQueryGetAllCasesByStatusArray(caseStatus);
		Table table = new Table(this,"c");
		Table caseTable = new Table(Case.class,"p");
		SelectQuery query = new SelectQuery(table);
		query.addColumn(table,CHILD_ID);
		query.setAsCountQuery(true);
		query.addJoin(table,getIDColumnName(),caseTable,"proc_case_id");
		query.addCriteria(new MatchCriteria(table,PROVIDER_ID,MatchCriteria.NOTEQUALS,providerID));
		query.addCriteria(new MatchCriteria(table,CHILD_ID,MatchCriteria.EQUALS,childID));
		query.addCriteria(new InCriteria(caseTable,"case_status",caseStatus));
		return idoGetNumberOfRecords(query);
	}

	public int ejbHomeGetNumberOfApplications(int providerID, String[] caseStatus, int sortBy, Date fromDate, Date toDate) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		if (sortBy == SORT_DATE_OF_BIRTH)
			sql.append(", ic_user u");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerID);
		if (caseStatus != null)
			sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);
		if (sortBy == SORT_DATE_OF_BIRTH) {
			sql.appendAndEquals("c."+CHILD_ID, "u.ic_user_id");
			sql.appendAnd().append("u.date_of_birth").appendGreaterThanOrEqualsSign().append(fromDate);
			sql.appendAnd().append("u.date_of_birth").appendLessThanOrEqualsSign().append(toDate);
		}
		else if (sortBy == SORT_QUEUE_DATE) {
			sql.appendAnd().append("c."+QUEUE_DATE).appendGreaterThanOrEqualsSign().append(fromDate);
			sql.appendAnd().append("c."+QUEUE_DATE).appendLessThanOrEqualsSign().append(toDate);
		}
		else if (sortBy == SORT_PLACEMENT_DATE) {
			sql.appendAnd().append("c."+FROM_DATE).appendGreaterThanOrEqualsSign().append(fromDate);
			sql.appendAnd().append("c."+FROM_DATE).appendLessThanOrEqualsSign().append(toDate);
		}
		//sql.appendAnd().appendEqualsQuoted("p.case_code",CASE_CODE_KEY);

		return idoGetNumberOfRecords(sql);
	}

    public int ejbHomeGetPositionInQueue(Date queueDate, int providerID, String[] caseStatus) throws IDOException {
        return ejbHomeGetPositionInQueue(queueDate, providerID, caseStatus, ORDER_BY_QUEUE_DATE);
    }
	public int ejbHomeGetPositionInQueue(Date queueDate, int providerID, String[] caseStatus, int orderBy) throws IDOException {
		Table table = new Table(this);
		Table caseTable = new Table(Case.class);
        Table userTable = null;

        SelectQuery query = new SelectQuery(table);
        query.addColumn(table,CHILD_ID);
        query.setAsCountQuery(true);
		query.addJoin(table,getIDColumnName(),caseTable,"proc_case_id");
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            userTable = new Table(User.class);
            query.addJoin(table, CHILD_ID, userTable, User.FIELD_USER_ID);
        }
		query.addCriteria(new MatchCriteria(table,PROVIDER_ID,MatchCriteria.EQUALS, providerID));
		query.addCriteria(new InCriteria(caseTable,"case_status",caseStatus,true));
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            if(queueDate != null) { // there are children without birth date
                query.addCriteria(new MatchCriteria(userTable, User.FIELD_DATE_OF_BIRTH, MatchCriteria.LESS, queueDate));
            }
        } else {
            query.addCriteria(new MatchCriteria(table,QUEUE_DATE,MatchCriteria.LESS,queueDate));
        }
		return idoGetNumberOfRecords(query);
	}


    public int ejbHomeGetPositionInQueue(Date queueDate, int providerID, String applicationStatus) throws IDOException {
        return ejbHomeGetPositionInQueue(queueDate, providerID, applicationStatus, ORDER_BY_QUEUE_DATE);
    }
	public int ejbHomeGetPositionInQueue(Date queueDate, int providerID, String applicationStatus, int orderBy) throws IDOException {
		Table table = new Table(this);
        Table userTable = null;

		SelectQuery query = new SelectQuery(table);
		query.addColumn(table,CHILD_ID);
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            userTable = new Table(User.class);
            query.addJoin(table, CHILD_ID, userTable, User.FIELD_USER_ID);
        }
		query.addCriteria(new MatchCriteria(table,PROVIDER_ID,MatchCriteria.EQUALS,providerID));
		query.addCriteria(new MatchCriteria(table,APPLICATION_STATUS,MatchCriteria.EQUALS,applicationStatus,true));
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            if(queueDate != null) { // there are children without birth date
                query.addCriteria(new MatchCriteria(userTable, User.FIELD_DATE_OF_BIRTH, MatchCriteria.LESS, queueDate));
            }
        } else {
            query.addCriteria(new MatchCriteria(table,QUEUE_DATE,MatchCriteria.LESS,queueDate));
        }

		query.setAsCountQuery(true);
		return idoGetNumberOfRecords(query);
	}

    public int ejbHomeGetPositionInQueueByDate(int queueOrder, Date queueDate, int providerID, String[] caseStatus) throws IDOException {
        return ejbHomeGetPositionInQueueByDate(queueOrder, queueDate, providerID, caseStatus, ORDER_BY_QUEUE_DATE);
    }
	public int ejbHomeGetPositionInQueueByDate(int queueOrder, Date queueDate, int providerID, String[] caseStatus, int orderBy) throws IDOException {
		Table table = new Table(this,"c");
		Table caseTable = new Table(Case.class,"p");
        Table userTable = null;

		SelectQuery query = new SelectQuery(table);
		query.addColumn(table,CHILD_ID);
		query.addJoin(table,getIDColumnName(),caseTable,"proc_case_id");
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            userTable = new Table(User.class, "u");
            query.addJoin(table, CHILD_ID, userTable, User.FIELD_USER_ID);
        }

		query.addCriteria(new MatchCriteria(table,PROVIDER_ID,MatchCriteria.EQUALS,providerID));

        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            if(queueDate != null) { // there are children without birth date
                query.addCriteria(new MatchCriteria(userTable, User.FIELD_DATE_OF_BIRTH, MatchCriteria.EQUALS, queueDate));
            }
        } else {
            query.addCriteria(new MatchCriteria(table,QUEUE_DATE,MatchCriteria.EQUALS,queueDate));
        }

		query.addCriteria(new MatchCriteria(table,QUEUE_ORDER,MatchCriteria.LESSEQUAL,queueOrder));
		//		 not in statuses, strange but seems to do it ( aron )
		query.addCriteria(new InCriteria(caseTable, "case_status",caseStatus,true));
		query.setAsCountQuery(true);
		return idoGetNumberOfRecords(query);
	}

    public int ejbHomeGetPositionInQueueByDate(int queueOrder, Date queueDate, int providerID, String applicationStatus) throws IDOException {
        return ejbHomeGetPositionInQueueByDate(queueOrder, queueDate, providerID, applicationStatus, ORDER_BY_QUEUE_DATE);
    }
	public int ejbHomeGetPositionInQueueByDate(int queueOrder, Date queueDate, int providerID, String applicationStatus, int orderBy) throws IDOException {
		Table table = new Table(this,"c");
        Table userTable = null;

		SelectQuery query = new SelectQuery(table);
		query.addColumn(table,CHILD_ID);
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            userTable = new Table(User.class, "u");
            query.addJoin(table, CHILD_ID, userTable, User.FIELD_USER_ID);
        }
		query.addCriteria(new MatchCriteria(table,PROVIDER_ID,MatchCriteria.EQUALS,providerID));
		query.addCriteria(new MatchCriteria(table,APPLICATION_STATUS,MatchCriteria.EQUALS,applicationStatus,true));
        if (orderBy == ORDER_BY_DATE_OF_BIRTH) {
            if(queueDate != null) { // there are children without birth date
                query.addCriteria(new MatchCriteria(userTable, User.FIELD_DATE_OF_BIRTH, MatchCriteria.EQUALS, queueDate));
            }
            //query.addCriteria(new MatchCriteria(table,QUEUE_ORDER,MatchCriteria.LESS,queueOrder));
        } else {
            query.addCriteria(new MatchCriteria(table,QUEUE_DATE,MatchCriteria.EQUALS,queueDate));
            //query.addCriteria(new MatchCriteria(table,QUEUE_ORDER,MatchCriteria.LESSEQUAL,queueOrder));
        }
        query.addCriteria(new MatchCriteria(table,QUEUE_ORDER,MatchCriteria.LESSEQUAL,queueOrder));

		query.setAsCountQuery(true);

		return idoGetNumberOfRecords(query);
	}

	public int ejbHomeGetQueueSizeNotInStatus(int providerID, String caseStatus[]) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerID);
		sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		//sql.appendAndEqualsQuoted("p.case_code",CASE_CODE_KEY);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetQueueSizeInStatus(int providerID, String applicationStatus[], Date from, Date to, boolean isOnlyFirstHand) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(" + CHILD_ID + ") from ").append(ENTITY_NAME);
		sql.appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAnd().append(APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatus);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		if (isOnlyFirstHand) {
			sql.appendAndEquals(CHOICE_NUMBER, 1);
		}
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetBruttoQueueSizeInStatus(int providerID, String applicationStatus[], Date from, Date to, boolean isOnlyFirstHand) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(" + CHILD_ID + ") from ").append(ENTITY_NAME);
		sql.appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAnd().append(APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatus);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		if (isOnlyFirstHand) {
			sql.appendAndEquals(CHOICE_NUMBER, 1);
		}
		Date today = new Date(System.currentTimeMillis());
		sql.appendAnd().append(CHILD_ID + " in ")
		.appendLeftParenthesis()
		.appendSelect().append("c.child_id").appendFrom()
		.append("comm_childcare_archive a,")
		.append("comm_childcare c")
		.appendWhereEquals("a.application_id", "c.comm_childcare_id")
		.appendAndEqualsQuoted("c.application_status", "F")
		.appendAnd().appendLeftParenthesis().append("a.terminated_date is null").appendOr()
		.append("a.terminated_date").appendGreaterThanOrEqualsSign().appendWithinSingleQuotes(today).appendRightParenthesis()
		.appendAnd().append("a.valid_from_date").appendLessThanSign().appendWithinSingleQuotes(today)
		.appendRightParenthesis();
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNettoQueueSizeInStatus(int providerID, String applicationStatus[], Date from, Date to, boolean isOnlyFirstHand) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(" + CHILD_ID + ") from ").append(ENTITY_NAME);
		sql.appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAnd().append(APPLICATION_STATUS).appendInArrayWithSingleQuotes(applicationStatus);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		if (isOnlyFirstHand) {
			sql.appendAndEquals(CHOICE_NUMBER, 1);
		}
		Date today = new Date(System.currentTimeMillis());
		sql.appendAnd().append(CHILD_ID + " not in ")
		.appendLeftParenthesis()
		.appendSelect().append("c.child_id").appendFrom()
		.append("comm_childcare_archive a,")
		.append("comm_childcare c")
		.appendWhereEquals("a.application_id", "c.comm_childcare_id")
		.appendAndEqualsQuoted("c.application_status", "F")
		.appendAnd().appendLeftParenthesis().append("a.terminated_date is null").appendOr()
		.append("a.terminated_date").appendGreaterThanOrEqualsSign().appendWithinSingleQuotes(today).appendRightParenthesis()
		.appendAnd().append("a.valid_from_date").appendLessThanSign().appendWithinSingleQuotes(today)
		.appendRightParenthesis();
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetQueueSizeInStatus(int providerID, String caseStatus) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,providerID);
		sql.appendAndEqualsQuoted("p.case_status",caseStatus);
		//sql.appendAndEqualsQuoted("p.case_code",CASE_CODE_KEY);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetQueueSizeInStatus(int providerID, String applicationStatus, Date from, Date to, boolean isOnlyFirstHand) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(" + CHILD_ID + ") from ").append(ENTITY_NAME);
		sql.appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAndEqualsQuoted(APPLICATION_STATUS, applicationStatus);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		if (isOnlyFirstHand) {
			sql.appendAndEquals(CHOICE_NUMBER, 1);
		}
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetBruttoQueueSizeInStatus(int providerID, String applicationStatus, Date from, Date to, boolean isOnlyFirstHand) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(" + CHILD_ID + ") from ").append(ENTITY_NAME);
		sql.appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAndEqualsQuoted(APPLICATION_STATUS, applicationStatus);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		if (isOnlyFirstHand) {
			sql.appendAndEquals(CHOICE_NUMBER, 1);
		}
		Date today = new Date(System.currentTimeMillis());
		sql.appendAnd().append(CHILD_ID + " in ")
		.appendLeftParenthesis()
		.appendSelect().append("c.child_id").appendFrom()
		.append("comm_childcare_archive a,")
		.append("comm_childcare c")
		.appendWhereEquals("a.application_id", "c.comm_childcare_id")
		.appendAndEqualsQuoted("c.application_status", "F")
		.appendAnd().appendLeftParenthesis().append("a.terminated_date is null").appendOr()
		.append("a.terminated_date").appendGreaterThanOrEqualsSign().appendWithinSingleQuotes(today).appendRightParenthesis()
		.appendAnd().append("a.valid_from_date").appendLessThanSign().appendWithinSingleQuotes(today)
		.appendRightParenthesis();
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNettoQueueSizeInStatus(int providerID, String applicationStatus, Date from, Date to, boolean isOnlyFirstHand) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(" + CHILD_ID + ") from ").append(ENTITY_NAME);
		sql.appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAndEqualsQuoted(APPLICATION_STATUS, applicationStatus);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		if (isOnlyFirstHand) {
			sql.appendAndEquals(CHOICE_NUMBER, 1);
		}
		Date today = new Date(System.currentTimeMillis());
		sql.appendAnd().append(CHILD_ID + " not in ")
		.appendLeftParenthesis()
		.appendSelect().append("c.child_id").appendFrom()
		.append("comm_childcare_archive a,")
		.append("comm_childcare c")
		.appendWhereEquals("a.application_id", "c.comm_childcare_id")
		.appendAndEqualsQuoted("c.application_status", "F")
		.appendAnd().appendLeftParenthesis().append("a.terminated_date is null").appendOr()
		.append("a.terminated_date").appendGreaterThanOrEqualsSign().appendWithinSingleQuotes(today).appendRightParenthesis()
		.appendAnd().append("a.valid_from_date").appendLessThanSign().appendWithinSingleQuotes(today)
		.appendRightParenthesis();
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetQueueSizeByAreaNotInStatus(int areaID, String caseStatus[]) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p, sch_school s");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("s.sch_school_id","c."+PROVIDER_ID);
		sql.appendAndEquals("s.sch_school_area_id",areaID);
		sql.appendAnd().append("p.case_status").appendNotInArrayWithSingleQuotes(caseStatus);
		//sql.appendAndEqualsQuoted("p.case_code",CASE_CODE_KEY);
		return idoGetNumberOfRecords(sql);

	}

	public int ejbHomeGetQueueSizeByAreaInStatus(int areaID, String caseStatus) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(c."+CHILD_ID+") from ").append(ENTITY_NAME).append(" c , proc_case p, sch_school s");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("s.sch_school_id","c."+PROVIDER_ID);
		sql.appendAndEquals("s.sch_school_area_id",areaID);
		sql.appendAndEqualsQuoted("p.case_status",caseStatus);
		//sql.appendAndEqualsQuoted("p.case_code",CASE_CODE_KEY);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfApplicationsByProviderAndChoiceNumber(int providerID, int choiceNumber) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetQueueByProviderAndChoiceNumber(int providerID, int choiceNumber, String status, Date from, Date to) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		sql.appendAndEqualsQuoted(APPLICATION_STATUS, status);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNettoQueueByProviderAndChoiceNumber(int providerID, int choiceNumber, String status, Date from, Date to) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		Date today = new Date(System.currentTimeMillis());
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		sql.appendAndEqualsQuoted(APPLICATION_STATUS, status)
		.appendAnd().append(CHILD_ID + " not in ")
		.appendLeftParenthesis()
		.appendSelect().append("c.child_id").appendFrom()
		.append("comm_childcare_archive a,")
		.append("comm_childcare c")
		.appendWhereEquals("a.application_id", "c.comm_childcare_id")
		.appendAndEqualsQuoted("c.application_status", "F")
		.appendAnd().appendLeftParenthesis().append("a.terminated_date is null").appendOr()
		.append("a.terminated_date").appendGreaterThanOrEqualsSign().appendWithinSingleQuotes(today).appendRightParenthesis()
		.appendAnd().append("a.valid_from_date").appendLessThanSign().appendWithinSingleQuotes(today)
		.appendRightParenthesis();
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetBruttoQueueByProviderAndChoiceNumber(int providerID, int choiceNumber, String status, Date from, Date to) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(PROVIDER_ID, providerID);
		sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		if (from == null) {
			from = new Date(0L);
		}
		if (to == null) {
			to = Date.valueOf("2999-01-01");
		}
		Date today = new Date(System.currentTimeMillis());
		sql.appendAnd().appendBetweenDates(FROM_DATE, from, to);
		sql.appendAndEqualsQuoted(APPLICATION_STATUS, status)
		.appendAnd().append(CHILD_ID + "in ")
		.appendLeftParenthesis()
		.appendSelect().append("c.child_id").appendFrom()
		.append("comm_childcare_archive a,")
		.append("comm_childcare c")
		.appendWhereEquals("a.application_id", "c.comm_childcare_id")
		.appendAndEqualsQuoted("c.application_status", "F")
		.appendAnd().appendLeftParenthesis().append("a.terminated_date is null").appendOr()
		.append("a.terminated_date").appendGreaterThanOrEqualsSign().appendWithinSingleQuotes(today).appendRightParenthesis()
		.appendAnd().append("a.valid_from_date").appendLessThanSign().appendWithinSingleQuotes(today)
		.appendRightParenthesis();
		return idoGetNumberOfRecords(sql);
	}

	public boolean isAcceptedByParent() {
		return 	getStatus().equals("PREL") && //CaseBMPBean.CASE_STATUS_PRELIMINARY_KEY (make public...?)
			getApplicationStatus() == 'D'; //ChildCareBusinessBean.STATUS_PARENTS_ACCEPT (make public...?)
	}

	public boolean isCancelledOrRejectedByParent() {
		return 	getStatus().equals("TYST") //CaseBMPBean.CASE_STATUS_INACTIVE_KEY (make public...?)
		&& (getApplicationStatus() == 'Z' //ChildCareBusinessBean.STATUS_CANCELLED (make public...?)
		|| getApplicationStatus() == 'V'); //ChildCareBusinessBean.STATUS_REJECTED (make public...?)
	}

	public boolean isActive(){
		Contract contract = getContract();
		java.util.Date today = new java.util.Date();

		if (contract != null) {
			if (contract.getValidTo() != null) {
//				if (contract.getValidFrom().compareTo(today) <= 0 && contract.getValidTo().compareTo(today) >= 0 && contract.isSigned()) {
				if (contract.getValidFrom().compareTo(today) <= 0 && contract.getValidTo().compareTo(today) >= 0) {
					return true;
				}
			} else {
//				if (contract.getValidFrom().compareTo(today) <= 0 && contract.isSigned()) {
				if (contract.getValidFrom().compareTo(today) <= 0) {
					return true;
				}
			}
		}
		return false;
	}

	public Collection ejbFindApplicationsInSchoolAreaByStatus(int schoolAreaID, String[] statuses, int choiceNumber) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).append(" c, proc_case p, sch_school s");
		sql.appendWhereEquals("c."+getIDColumnName(), "p.proc_case_id");
		sql.appendAndEquals("c."+PROVIDER_ID,"s.sch_school_id");
		sql.appendAndEquals("s.sch_school_area_id", schoolAreaID);
		if (choiceNumber != -1) {
			sql.appendAndEquals(CHOICE_NUMBER, choiceNumber);
		}
		sql.appendAnd().append("p.case_status").appendInArrayWithSingleQuotes(statuses);
		sql.appendOrderBy("c."+APPLICATION_STATUS+" desc, c."+QUEUE_DATE+", c."+QUEUE_ORDER);
		return idoFindPKsByQuery(sql);
	}

	public void addSubscriber(User arg0) throws IDOAddRelationshipException {
	}

	public Collection<User> getSubscribers() {
		return null;
	}

	public void removeSubscriber(User arg0)	throws IDORemoveRelationshipException {
	}

}