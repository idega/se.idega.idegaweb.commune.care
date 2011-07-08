/*
 * Created on 26.3.2003
 */
package se.idega.idegaweb.commune.care.data;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.block.contract.data.Contract;
import com.idega.block.school.data.School;
import com.idega.block.school.data.SchoolClassBMPBean;
import com.idega.block.school.data.SchoolClassMember;
import com.idega.block.school.data.SchoolClassMemberBMPBean;
import com.idega.core.file.data.ICFile;
import com.idega.data.GenericEntity;
import com.idega.data.IDOException;
import com.idega.data.IDOQuery;
import com.idega.data.IDORelationshipException;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;
import com.idega.util.TimePeriod;

/**
 * The main class of a childcare contract. Is thought to be an extension of the standard Contract object (com.idega.block.contract.data.Contract).
 * @author laddi
 */
public class ChildCareContractBMPBean extends GenericEntity implements ChildCareContract {

	public static final String ENTITY_NAME = "comm_childcare_archive";
	
	public static final String COLUMN_CHILD_ID = "child_id";
	public static final String COLUMN_APPLICATION_ID = "application_id";
	public final static String COLUMN_CONTRACT_ID = "contract_id";
	public static final String COLUMN_CONTRACT_FILE_ID = "contract_file_id";
	public static final String COLUMN_SCH_CLASS_MEMBER = "sch_class_member_id";
	public static final String COLUMN_CREATED_DATE = "created_date";
	public static final String COLUMN_VALID_FROM_DATE = "valid_from_date";
	public static final String COLUMN_TERMINATED_DATE = "terminated_date";
	public static final String COLUMN_CARE_TIME = "care_time_string";
	public static final String COLUMN_CARE_TIME_OLD = "care_time";
	public static final String COLUMN_WORK_SITUATION = "work_situation";
	public static final String COLUMN_INVOICE_RECEIVER = "invoice_receiver";
	
	/**
	 * @see com.idega.data.IDOLegacyEntity#getEntityName()
	 */
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/**
	 * @see com.idega.data.IDOLegacyEntity#initializeAttributes()
	 */
	public void initializeAttributes() {
		addAttribute(getIDColumnName());
		addAttribute(COLUMN_CREATED_DATE,"",true,true,java.sql.Date.class);
		addAttribute(COLUMN_VALID_FROM_DATE,"",true,true,java.sql.Date.class);
		addAttribute(COLUMN_TERMINATED_DATE,"",true,true,java.sql.Date.class);
		addAttribute(COLUMN_CARE_TIME_OLD,"",true,true,java.lang.Integer.class);
		addAttribute(COLUMN_CARE_TIME,"",true,true,java.lang.String.class);
		
		addManyToOneRelationship(COLUMN_CHILD_ID,User.class);
		addManyToOneRelationship(COLUMN_APPLICATION_ID,ChildCareApplication.class);
		addOneToOneRelationship(COLUMN_CONTRACT_FILE_ID,ICFile.class);
		addOneToOneRelationship(COLUMN_CONTRACT_ID,Contract.class);
		addOneToOneRelationship(COLUMN_SCH_CLASS_MEMBER,SchoolClassMember.class);
		addManyToOneRelationship(COLUMN_WORK_SITUATION,EmploymentType.class);
		addManyToOneRelationship(COLUMN_INVOICE_RECEIVER,User.class);
	}
	
	public Date getCreatedDate() {
		return (Date) getColumnValue(COLUMN_CREATED_DATE);
	}

	public Date getValidFromDate() {
		return (Date) getColumnValue(COLUMN_VALID_FROM_DATE);
	}
	
	public Date getTerminatedDate() {
		return (Date) getColumnValue(COLUMN_TERMINATED_DATE);
	}
	
	public String getCareTime() {
		String careTime = getStringColumnValue(COLUMN_CARE_TIME);
		if (careTime == null) {
			int oldCareTime = getIntColumnValue(COLUMN_CARE_TIME_OLD);
			if (oldCareTime != -1) {
				careTime = String.valueOf(oldCareTime);
			}
		}
		return careTime;
	}
	
	public int getChildID() {
		return getIntColumnValue(COLUMN_CHILD_ID);
	}
	
	public User getChild() {
		return (User) getColumnValue(COLUMN_CHILD_ID);
	}
	
	public int getApplicationID() {
		return getIntColumnValue(COLUMN_APPLICATION_ID);
	}
	
	public ChildCareApplication getApplication() {
		return (ChildCareApplication) getColumnValue(COLUMN_APPLICATION_ID);
	}

	public int getContractID() {
		return getIntColumnValue(COLUMN_CONTRACT_ID);
	}
	
	public Contract getContract() {
		return (Contract) getColumnValue(COLUMN_CONTRACT_ID);
	}
	
	public int getContractFileID() {
		return getIntColumnValue(COLUMN_CONTRACT_FILE_ID);
	}
	
	public ICFile getContractFile() {
		return (ICFile) getColumnValue(COLUMN_CONTRACT_FILE_ID);
	}
	
	public SchoolClassMember getSchoolClassMember() {
		return (SchoolClassMember) getColumnValue(COLUMN_SCH_CLASS_MEMBER);
	}
	
	public int getSchoolClassMemberId() {
		return getIntColumnValue(COLUMN_SCH_CLASS_MEMBER);
	}
	
	public int getEmploymentTypeId(){
		return getIntColumnValue(COLUMN_WORK_SITUATION);
	}
	
	public EmploymentType getEmploymentType() {
		return (EmploymentType) getColumnValue(COLUMN_WORK_SITUATION);
	}
	
	public int getInvoiceReceiverID() {
		return getIntColumnValue(COLUMN_INVOICE_RECEIVER);
	}
	
	public User getInvoiceReceiver() {
		return (User) getColumnValue(COLUMN_INVOICE_RECEIVER);
	}
	

	public void setCreatedDate(Date createdDate) {
		setColumn(COLUMN_CREATED_DATE, createdDate);
	}

	public void setValidFromDate(Date validFromDate) {
		setColumn(COLUMN_VALID_FROM_DATE, validFromDate);
	}
	
	public void setTerminatedDate(Date terminatedDate) {
		setColumn(COLUMN_TERMINATED_DATE, terminatedDate);
	}
	
	public void setCareTime(String careTime) {
		setColumn(COLUMN_CARE_TIME, careTime);
	}
	
	public void setChildID(int childID) {
		setColumn(COLUMN_CHILD_ID, childID);
	}
	
	public void setChild(User child) {
		setColumn(COLUMN_CHILD_ID, child);
	}
	
	public void setApplicationID(int applicationID) {
		setColumn(COLUMN_APPLICATION_ID, applicationID);
	}
	
	public void setApplication(ChildCareApplication application) {
		setColumn(COLUMN_APPLICATION_ID, application);
	}
	
	public void setContractID(int contractID) {
		setColumn(COLUMN_CONTRACT_ID, contractID);
	}
	
	public void setContract(Contract contract) {
		setColumn(COLUMN_CONTRACT_ID, contract);
	}
	
	public void setContractFileID(int contractFileID) {
		setColumn(COLUMN_CONTRACT_FILE_ID, contractFileID);
	}
	
	public void setContractFile(ICFile contractFile) {
		setColumn(COLUMN_CONTRACT_FILE_ID, contractFile);
	}
	
	public void setSchoolClassMemberID(int schoolClassMemberID) {
		setColumn(COLUMN_SCH_CLASS_MEMBER, schoolClassMemberID);
	}
	
	public void setSchoolClassMember(SchoolClassMember schoolClassMember) {
		setColumn(COLUMN_SCH_CLASS_MEMBER, schoolClassMember);
	}
	
	public void setTerminationDateAsNull(boolean setAsNull) {
		if (setAsNull)
			removeFromColumn(COLUMN_TERMINATED_DATE);
	}
	
	public void setEmploymentType(int employmentTypeID) {
		setColumn(COLUMN_WORK_SITUATION, employmentTypeID);
	}
	
	public void setInvoiceReceiverID(int invoiceReciverID) {
		setColumn(COLUMN_INVOICE_RECEIVER, invoiceReciverID);
	}
	
	public void setInvoiceReceiverID(Integer invoiceReciverID) {
		setColumn(COLUMN_INVOICE_RECEIVER, invoiceReciverID);
	}
	
	public void setInvoiceReceiver(User invoiceReciver) {
		setColumn(COLUMN_INVOICE_RECEIVER, invoiceReciver);
	}
	
	public Collection ejbFindByChild(int childID) throws FinderException {
	    return ejbFindByChild(childID,-1,-1);
	
	}
	public Collection ejbFindByChild(int childID,int resultSize,int startIndex) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, childID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return idoFindPKsByQuery(sql,resultSize,startIndex);
	}
	
	public Collection ejbFindByChildAndDateRange (User child, Date startDate, Date endDate) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, child);
		sql.appendAnd ();
		sql.appendLeftParenthesis ();
		sql.appendWithinDates (COLUMN_VALID_FROM_DATE, startDate, endDate);
		sql.appendOr ();
		sql.appendLeftParenthesis ();
		sql.append (COLUMN_VALID_FROM_DATE);
		sql.appendLessThanOrEqualsSign ();
		sql.append (startDate);
		sql.appendAnd ();
		sql.appendLeftParenthesis ();
		sql.append (COLUMN_TERMINATED_DATE).appendIsNull ();
		sql.appendOr ();
		sql.append (startDate);
		sql.appendLessThanOrEqualsSign ();
		sql.append (COLUMN_TERMINATED_DATE);
		sql.appendRightParenthesis ();
		sql.appendRightParenthesis ();
		sql.appendRightParenthesis ();
		return idoFindPKsByQuery(sql);
	}
	
	public Collection ejbFindByChildAndProvider(int childID, int providerID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(getEntityName()).append(" a, ").append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" c");
		sql.appendWhereEquals("a."+COLUMN_CHILD_ID, childID);
		sql.appendAndEquals("a."+COLUMN_CHILD_ID, "c."+ChildCareApplicationBMPBean.CHILD_ID);
		sql.appendAndEquals("c."+ChildCareApplicationBMPBean.PROVIDER_ID, providerID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return idoFindPKsByQuery(sql);
	}
	
	public Collection ejbFindByApplication(int applicationID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return idoFindPKsByQuery(sql);
	}

	public Integer ejbFindValidContractByApplication(int applicationID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(date);
		sql.appendAnd().appendLeftParenthesis().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(date);
		sql.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	public Integer ejbFindValidContractByPlacement(SchoolClassMember member, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_SCH_CLASS_MEMBER, member);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(date);
		sql.appendAnd().appendLeftParenthesis().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(date);
		sql.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	public Collection ejbFindValidContractByProvider(int providerID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(getEntityName()).append(" c, ");
		sql.append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" a");
		sql.appendWhere().append("c."+COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(date);
		sql.appendAnd().appendLeftParenthesis().append("c."+COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(date);
		sql.appendOr().append("c."+COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		sql.appendAndEquals("c."+COLUMN_APPLICATION_ID,"a."+ChildCareApplicationBMPBean.ENTITY_NAME+"_id");
		sql.appendAndEquals("a."+ChildCareApplicationBMPBean.PROVIDER_ID, providerID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return idoFindPKsByQuery(sql);
	}
	
	public Integer ejbFindApplicationByContract(int contractID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CONTRACT_ID, contractID);
		return (Integer) idoFindOnePKByQuery(sql);
	}	
	
	public Integer ejbFindValidContractByChild(int childID) throws FinderException {
		IWTimestamp stamp = new IWTimestamp();
		return ejbFindValidContractByChild(childID, stamp.getDate());
	}
	
	public Integer ejbFindValidContractByChild(int childID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, childID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(date);
		sql.appendAnd().appendLeftParenthesis().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(date);
		sql.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindContractByChildAndPeriod(User child, TimePeriod period) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this);
		sql.appendWhereEquals(COLUMN_CHILD_ID, child);
		sql.appendAnd();
		sql.append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(period.getLastTimestamp().getDate ());
		sql.appendAnd();
		sql.appendLeftParenthesis ();
		sql.append(COLUMN_TERMINATED_DATE).appendGreaterThanOrEqualsSign().append(period.getFirstTimestamp().getDate ());
		sql.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null");
		sql.appendRightParenthesis();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindLatestTerminatedContractByChild(int childID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, childID);
		if(date!=null){
		    sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(date);
			sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(date);
		}
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendIsNotNull();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	public Integer ejbFindNextContractByChild(ChildCareContract contract) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, contract.getChildID());
		if(contract.getTerminatedDate()!=null){
		    sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendGreaterThanSign().append(contract.getTerminatedDate());
		}
		else {
	    sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendGreaterThanSign().append(contract.getValidFromDate());
		}
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE);
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	public Integer ejbFindNextTerminatedContractByChild(ChildCareContract contract) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, contract.getChildID());
		if(contract.getValidFromDate()!=null){
		    sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendGreaterThanSign().append(contract.getValidFromDate());
			sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendGreaterThanOrEqualsSign().append(contract.getValidFromDate());
		}
		sql.appendAnd().append(getIDColumnName()).appendNOTEqual().append(contract.getPrimaryKey());
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendIsNotNull();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	public Integer ejbFindPreviousTerminatedContractByChild(ChildCareContract contract) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, contract.getChildID());
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendLessThanSign().append(contract.getValidFromDate());
		sql.appendAnd().append(getIDColumnName()).appendNOTEqual().append(contract.getPrimaryKey());
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendIsNotNull();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	/**
	 * Gets the contract which stands before the supplied one
	 * @param contract
	 * @return
	 * @throws FinderException
	 */
	public Integer ejbFindPreviousTerminatedContractByContract(ChildCareContract contract) throws FinderException {
		IDOQuery sql = idoQuery();		
		sql.appendSelectAllFrom(this);
		sql.appendWhere();
		sql.append(COLUMN_APPLICATION_ID).appendEqualSign().append(contract.getApplication());
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendIsNotNull();
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendLessThanSign().append(contract.getValidFromDate());
		sql.appendAnd().append(getIDColumnName()).appendNOTEqual().append(contract.getPrimaryKey());		
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE + " desc");
		
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	/** 
	 * Finds the contract that stands after the supplied one
	 * @param contract
	 * @return
	 * @throws FinderException
	 */
	public Integer ejbFindNextContractByContract(ChildCareContract contract) throws FinderException {
		IDOQuery sql = idoQuery();		
		sql.appendSelectAllFrom(this);
		sql.appendWhere();
		sql.append(COLUMN_APPLICATION_ID).appendEqualSign().append(contract.getApplication());
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendGreaterThanSign();
		if (contract.getTerminatedDate() != null) {
			sql.append(contract.getTerminatedDate());
		} else {
			sql.append(contract.getValidFromDate());
		}		
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE + " asc");
		
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindLatestContractByChild(int childID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CHILD_ID, childID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindLatestContractByApplication(int applicationID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	public Collection ejbFindLatestByApplication(int applicationID,int maxNumberOfContracts) throws  FinderException{
	    IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return idoFindPKsByQuery(sql,maxNumberOfContracts);
	}

	public Integer ejbFindLatestNotByApplication(int applicationID, Date startDate) throws  FinderException{
    IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhere().append(COLUMN_APPLICATION_ID).appendNOTEqual().append(applicationID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanSign().append(startDate);
		sql.appendOrderByDescending(COLUMN_VALID_FROM_DATE);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindFirstContractByApplication(int applicationID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindContractByApplicationAndDate(int applicationID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(date);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE).append(" desc");
		return (Integer) idoFindOnePKByQuery(sql);
	}
	
	public Collection ejbFindFutureContractsByApplication(int applicationID, Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendGreaterThanSign().append(date);
		return idoFindPKsByQuery(sql);
	}

	public int ejbHomeGetNumberOfActiveNotWithProvider(int childID, int providerID) throws IDOException {
		return ejbHomeGetNumberOfActiveNotWithProvider(childID, providerID, null);
	}
	
	public int ejbHomeGetNumberOfActiveNotWithProvider(int childID, int providerID, Date date) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(*) from ").append(this.getEntityName()).append(" a, comm_childcare c");
		sql.appendWhereEquals("a."+COLUMN_APPLICATION_ID, "c.comm_childcare_id");
		sql.appendAndEquals("a." + COLUMN_CHILD_ID, childID);
		sql.appendAnd().append("c.provider_id").appendNOTEqual().append(providerID);
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).append(" is null");
		if (date != null) {
			sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendGreaterThanSign().append(date);
		}
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetNumberOfActiveForApplication(int applicationID, Date date) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(date);
		sql.appendAnd().appendLeftParenthesis().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(date);
		sql.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE+" desc");
		return idoGetNumberOfRecords(sql);
	}


	public int ejbHomeGetNumberOfTerminatedLaterNotWithProvider(int childID, int providerID, Date date) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(*) from ").append(this.getEntityName()).append(" a, comm_childcare c");
		sql.appendWhereEquals("a."+COLUMN_APPLICATION_ID, "c.comm_childcare_id");
		sql.appendAndEquals("a." + COLUMN_CHILD_ID, childID);
		if (providerID != -1) {
			sql.appendAnd().append("c.provider_id").appendNOTEqual().append(providerID);
		}
		sql.appendAnd().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(date);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetFutureContractsCountByApplication(int applicationID, Date date) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendGreaterThanOrEqualsSign().append(date);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetContractsCountByApplication(int applicationID) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(COLUMN_APPLICATION_ID, applicationID);
		return idoGetNumberOfRecords(sql);
	}

	public int ejbHomeGetContractsCountByDateRangeAndProvider(Date startDate, Date endDate, int providerID) throws IDOException {
		IDOQuery sql = idoQuery();
		sql.append("select count(*) from ").append(this.getEntityName()).append(" a, ").append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" c");
		sql.appendWhereEquals("a."+COLUMN_CHILD_ID, "c."+ChildCareApplicationBMPBean.CHILD_ID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(endDate);
		sql.appendAnd().appendLeftParenthesis().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(startDate);
		sql.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		sql.appendAndEquals("c."+ChildCareApplicationBMPBean.PROVIDER_ID, providerID);
		return idoGetNumberOfRecords(sql);
	}

	public Integer ejbFindByContractFileID(int contractFileID) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_CONTRACT_FILE_ID, contractFileID);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Collection ejbFindByDateRange(Date startDate, Date endDate) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelect().append(" distinct a.* from "+getEntityName()).append(" a, ").append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" c");
		sql.appendWhereEquals("a."+COLUMN_CHILD_ID, "c."+ChildCareApplicationBMPBean.CHILD_ID);
		sql.appendAnd().append(COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(endDate);
		sql.appendAnd().appendLeftParenthesis().append(COLUMN_TERMINATED_DATE).appendGreaterThanSign().append(startDate);
		sql.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		return idoFindPKsByQuery(sql);
	}

	public Collection ejbFindChangedSinceDate(Date date) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelect().append(" distinct a.* from "+getEntityName()).append(" a, ").append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" c");
		sql.appendWhereEquals("a."+COLUMN_CHILD_ID, "c."+ChildCareApplicationBMPBean.CHILD_ID);
		sql.appendAnd().appendLeftParenthesis();
		sql.append(COLUMN_VALID_FROM_DATE).appendGreaterThanOrEqualsSign().append(date);
		sql.appendOr().append(COLUMN_TERMINATED_DATE).appendGreaterThanOrEqualsSign().append(date);
		sql.appendRightParenthesis();
		return idoFindPKsByQuery(sql);
	}

	public Collection ejbFindChangedBetween(Date from, Date to) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelect().append(" distinct a.* from ").append(getEntityName()).append(" a ");
		sql.append("left join sch_class_member m ");
		sql.append("on m.sch_class_member_id = a.sch_class_member_id ");
		sql.append("left join sch_class_member_log l ");
		sql.append("on l.sch_class_member_id = m.sch_class_member_id");
		sql.appendWhere();
		sql.appendLeftParenthesis();		
		sql.append("a." + COLUMN_VALID_FROM_DATE).appendGreaterThanOrEqualsSign().append(from);
		sql.appendAnd().append("a." + COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(to);
		sql.appendRightParenthesis();
		sql.appendOr().appendLeftParenthesis();		
		sql.append("a." + COLUMN_TERMINATED_DATE).appendGreaterThanOrEqualsSign().append(from);
		sql.appendAnd().append("a." + COLUMN_TERMINATED_DATE).appendLessThanOrEqualsSign().append(to);
		sql.appendRightParenthesis();
		sql.appendOr().appendLeftParenthesis();		
		sql.append("l.start_date").appendGreaterThanOrEqualsSign().append(from);
		sql.appendAnd().append("l.start_date").appendLessThanOrEqualsSign().append(to);
		sql.appendRightParenthesis();
		sql.appendOr().appendLeftParenthesis();		
		sql.append("l.end_date").appendGreaterThanOrEqualsSign().append(from);
		sql.appendAnd().append("l.end_date").appendLessThanOrEqualsSign().append(to);
		sql.appendRightParenthesis();
		return idoFindPKsByQuery(sql);
	}

	public Collection ejbFindByDateRangeAndProviderWhereStatusActive(Date startDate, Date endDate,School school) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelect().append(" distinct a.COMM_CHILDCARE_ARCHIVE_ID from "+getEntityName()).append(" a, ")
		.append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" c, ");
		sql.append(SchoolClassMemberBMPBean.SCHOOLCLASSMEMBER).append(" scm, ");
		sql.append(SchoolClassBMPBean.SCHOOLCLASS).append(" sc ");
		sql.appendWhereEquals("a."+COLUMN_APPLICATION_ID, "c.COMM_CHILDCARE_ID");
		sql.appendAnd();
		sql.appendEquals("a."+COLUMN_SCH_CLASS_MEMBER,"scm."+SchoolClassMemberBMPBean.SCHOOLCLASSMEMBERID);
		sql.appendAnd();
		sql.appendEquals("sc."+SchoolClassBMPBean.SCHOOLCLASSID,"scm."+SchoolClassMemberBMPBean.SCHOOLCLASS);
		sql.appendAnd();
		sql.appendEquals("sc."+SchoolClassBMPBean.SCHOOL,school.getPrimaryKey().toString());
/*		sql.appendAnd().appendLeftParenthesis();
		sql.appendEqualsQuoted("c.APPLICATION_STATUS","F");
		sql.appendOr().appendEqualsQuoted("c.APPLICATION_STATUS","V");
		sql.appendRightParenthesis();*/
		sql.appendAnd();
		sql.append("c.APPLICATION_STATUS in ").appendLeftParenthesis();
		sql.append("'F', 'V', 'R', 'P'").appendRightParenthesis();
		sql.appendAnd().append("a."+COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(endDate);
		sql.appendAnd().appendLeftParenthesis().append("a."+COLUMN_TERMINATED_DATE).appendGreaterThanOrEqualsSign().append(startDate);
		sql.appendOr().append("a."+COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();

		//Temp Patch for Lotta until they have fixed the problem
//		sql.appendAnd().append("a."+COLUMN_CARE_TIME).appendGreaterThanSign().append("0");

		System.out.println("SQL: "+sql);
		return idoFindPKsByQuery(sql);
	}	
	
	
	public Collection ejbFindByDateRangeWhereStatusActive(Date startDate, Date endDate) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelect().append(" distinct a.COMM_CHILDCARE_ARCHIVE_ID from "+getEntityName()).append(" a, ").append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" c");
		sql.appendWhereEquals("a."+COLUMN_APPLICATION_ID, "c.COMM_CHILDCARE_ID");
/*		sql.appendAnd().appendLeftParenthesis();
		sql.appendEqualsQuoted("c.APPLICATION_STATUS","F");
		sql.appendOr().appendEqualsQuoted("c.APPLICATION_STATUS","V");
		sql.appendRightParenthesis();*/
		sql.appendAnd();
		sql.append("c.APPLICATION_STATUS in ").appendLeftParenthesis();
		sql.append("'F', 'V', 'R', 'P'").appendRightParenthesis();
		sql.appendAnd().append("a."+COLUMN_VALID_FROM_DATE).appendLessThanOrEqualsSign().append(endDate);
		sql.appendAnd().appendLeftParenthesis().append("a."+COLUMN_TERMINATED_DATE).appendGreaterThanOrEqualsSign().append(startDate);
		sql.appendOr().append("a."+COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();

		//Temp Patch for Lotta until they have fixed the problem
//		sql.appendAnd().append("a."+COLUMN_CARE_TIME).appendGreaterThanSign().append("0");

//		System.out.println("SQL: "+sql);
		return idoFindPKsByQuery(sql);
	}

	public Integer ejbFindBySchoolClassMember (SchoolClassMember placement) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_SCH_CLASS_MEMBER, placement);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Integer ejbFindLatestBySchoolClassMember (SchoolClassMember placement) throws FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_SCH_CLASS_MEMBER, placement);
		sql.appendOrderByDescending(COLUMN_VALID_FROM_DATE);
		return (Integer) idoFindOnePKByQuery(sql);
	}

	public Collection ejbFindAll() throws FinderException {
		IDOQuery sql = idoQueryGetSelect();
		return idoFindPKsByQuery(sql);
	}
	
	public Collection ejbFindByInvoiceReceiver(Integer invoiceReceiverID)throws FinderException{
		IDOQuery query = idoQueryGetSelect();
		query.appendWhereEquals(COLUMN_INVOICE_RECEIVER,invoiceReceiverID);
		return super.idoFindPKsByQuery(query);
	}

	public Collection ejbFindByInvoiceReceiverActiveOrFuture(Integer invoiceReceiverID,Date fromDate)throws FinderException{
		IDOQuery query = idoQueryGetSelect();
		query.appendWhereEquals(COLUMN_INVOICE_RECEIVER,invoiceReceiverID);
		query.appendAnd().appendLeftParenthesis().append(COLUMN_TERMINATED_DATE).appendGreaterThanOrEqualsSign().append(fromDate);
		query.appendOr().append(COLUMN_TERMINATED_DATE).append(" is null").appendRightParenthesis();
		query.appendOrderBy(COLUMN_VALID_FROM_DATE).appendDescending();
		return super.idoFindPKsByQuery(query);
	}
	
	public Collection ejbFindAllBySchoolClassMember(SchoolClassMember member)throws FinderException{
	    IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this).appendWhereEquals(COLUMN_SCH_CLASS_MEMBER, member);
		sql.appendOrderBy(COLUMN_VALID_FROM_DATE);
		return idoFindPKsByQuery(sql);
	}
	
	public int ejbHomeGetCountBySchoolClassMember(SchoolClassMember member) throws IDOException {
    IDOQuery sql = idoQuery();
		sql.appendSelectCountFrom(this).appendWhereEquals(COLUMN_SCH_CLASS_MEMBER, member);
		return idoGetNumberOfRecords(sql);
	}
	
	public Collection ejbFindAllByCareTimeAndDates(String careTime, Date startingBefore, Date fromBirthDate, Date toBirthDate) throws FinderException {
		Table table = new Table(this);
		Table user = new Table(User.class);
		
		SelectQuery query = new SelectQuery(table);
		query.addColumn(table, getIDColumnName());
		try {
			query.addJoin(table, user);
		}
		catch (IDORelationshipException ire) {
			throw new FinderException(ire.getMessage());
		}
		query.addCriteria(new MatchCriteria(table, COLUMN_CARE_TIME, MatchCriteria.EQUALS, careTime));
		query.addCriteria(new MatchCriteria(table.getColumn(COLUMN_TERMINATED_DATE), false));
		query.addCriteria(new MatchCriteria(table, COLUMN_VALID_FROM_DATE, MatchCriteria.LESSEQUAL, startingBefore));
		query.addCriteria(new MatchCriteria(user, "date_of_birth", MatchCriteria.GREATEREQUAL, fromBirthDate));
		query.addCriteria(new MatchCriteria(user, "date_of_birth", MatchCriteria.LESSEQUAL, toBirthDate));
		
		return idoFindPKsByQuery(query);
	}
    
    /**
     * Finds contracts, according to params
     * 
     * @return
     * @throws FinderException
     */
    public Collection ejbFindAllByProviderAndClassMemberDateRange(Integer schoolId,
            Date startFrom, Date startTo, Date endFrom, Date endTo)
            throws FinderException {        
        IDOQuery query = idoQuery();
        query.appendSelect();
        
        query.append(" cca.comm_childcare_archive_id ");                 
        
        query.appendFrom();
        query.append(getEntityName()).append(" cca, ");
        query.append(SchoolClassMemberBMPBean.SCHOOLCLASSMEMBER).append(" scm, ");
        query.append(ChildCareApplicationBMPBean.ENTITY_NAME).append(" cc ");        
        query.appendWhere();
        query.appendEquals("cca.sch_class_member_id", "scm.sch_class_member_id");
        query.appendAndEquals("cca.application_id", "cc.comm_childcare_id");
        
        if (schoolId != null) {
            query.appendAndEquals("cc.provider_id", schoolId);    
        }
        
        if(startFrom != null) {
        	query.appendAnd().append("scm.register_date >= ").append(startFrom);
        }
        if (startTo != null) { 
        	query.appendAnd().append("scm.register_date <= ").append(startTo);
        }
        if (endFrom != null){ 
        	query.appendAnd().append("scm.removed_date >= ").append(endFrom);
        }
        if (endTo != null) { 
        	query.appendAnd().append("scm.removed_date <= ").append(endTo);
        }
        query.appendAnd().append(" cca.comm_childcare_archive_id = ");
        query.append("(select max(comm_childcare_archive_id) from comm_childcare_archive cca_sub");
        query.append(" where cca_sub.application_id = cca.application_id)"); 
        query.append(" order by scm.removed_date - cc.cancel_date_requested desc ");
                
        return idoFindPKsByQuery(query);
    }
}