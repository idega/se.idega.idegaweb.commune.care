/**
 * 
 */
package se.idega.idegaweb.commune.care.data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import se.idega.idegaweb.commune.care.check.data.GrantedCheck;
import com.idega.block.contract.data.Contract;
import com.idega.block.process.data.Case;
import com.idega.block.school.data.School;
import com.idega.core.file.data.ICFile;
import com.idega.data.IDOEntity;
import com.idega.user.data.User;


/**
 * <p>
 * TODO Dainis Describe Type ChildCareApplication
 * </p>
 *  Last modified: $Date: 2006/04/04 14:37:53 $ by $Author: dainis $
 * 
 * @author <a href="mailto:Dainis@idega.com">Dainis</a>
 * @version $Revision: 1.8.2.4 $
 */
public interface ChildCareApplication extends IDOEntity, Case {

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCaseCodeKey
	 */
	public String getCaseCodeKey();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCaseCodeDescription
	 */
	public String getCaseCodeDescription();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getFee
	 */
	public float getFee();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setFee
	 */
	public void setFee(float fee);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getFromTime
	 */
	public Time getFromTime();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setFromTime
	 */
	public void setFromTime(Timestamp stamp);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getToTime
	 */
	public Time getToTime();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setToTime
	 */
	public void setToTime(Timestamp stamp);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getProviderId
	 */
	public int getProviderId();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getProvider
	 */
	public School getProvider();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getFromDate
	 */
	public Date getFromDate();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getChildId
	 */
	public int getChildId();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getChild
	 */
	public User getChild();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getQueueDate
	 */
	public Date getQueueDate();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getMethod
	 */
	public int getMethod();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCareTime
	 */
	public String getCareTime();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getChoiceNumber
	 */
	public int getChoiceNumber();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCheckId
	 */
	public int getCheckId();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCheck
	 */
	public GrantedCheck getCheck();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getRejectionDate
	 */
	public Date getRejectionDate();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getOfferValidUntil
	 */
	public Date getOfferValidUntil();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getLastReplyDate
	 */
	public Date getLastReplyDate();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getContractId
	 */
	public int getContractId();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getContract
	 */
	public Contract getContract();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getContractFileId
	 */
	public int getContractFileId();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getContractFile
	 */
	public ICFile getContractFile();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getPrognosis
	 */
	public String getPrognosis();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getPresentation
	 */
	public String getPresentation();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getPreSchool
	 */
	public String getPreSchool();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getMessage
	 */
	public String getMessage();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getQueueOrder
	 */
	public int getQueueOrder();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getApplicationStatus
	 */
	public char getApplicationStatus();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getHasPriority
	 */
	public boolean getHasPriority();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getHasDateSet
	 */
	public boolean getHasDateSet();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getHasQueuePriority
	 */
	public boolean getHasQueuePriority();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getHasExtraContract
	 */
	public boolean getHasExtraContract();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getExtraContractMessage
	 */
	public String getExtraContractMessage();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getHasExtraContractOther
	 */
	public boolean getHasExtraContractOther();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getExtraContractMessageOther
	 */
	public String getExtraContractMessageOther();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getRequestedCancelDate
	 */
	public Date getRequestedCancelDate();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCancelMessage
	 */
	public String getCancelMessage();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getParentalLeave
	 */
	public boolean getParentalLeave();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCancelFormFile
	 */
	public ICFile getCancelFormFile();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCancelFormFileID
	 */
	public int getCancelFormFileID();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCancelConfirmationReceived
	 */
	public Date getCancelConfirmationReceived();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getFromDateRequested
	 */
	public Date getFromDateRequested();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCancelRequestReceived
	 */
	public Date getCancelRequestReceived();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#getCancelDateRequested
	 */
	public Date getCancelDateRequested();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setProviderId
	 */
	public void setProviderId(int id);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setProvider
	 */
	public void setProvider(School provider);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setFromDate
	 */
	public void setFromDate(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setChildId
	 */
	public void setChildId(int id);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setChild
	 */
	public void setChild(User child);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setQueueDate
	 */
	public void setQueueDate(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setMethod
	 */
	public void setMethod(int method);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCareTime
	 */
	public void setCareTime(String careTime);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setChoiceNumber
	 */
	public void setChoiceNumber(int number);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCheckId
	 */
	public void setCheckId(int checkId);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCheck
	 */
	public void setCheck(GrantedCheck check);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setRejectionDate
	 */
	public void setRejectionDate(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setOfferValidUntil
	 */
	public void setOfferValidUntil(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setLastReplyDate
	 */
	public void setLastReplyDate(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setContractId
	 */
	public void setContractId(int id);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setContractId
	 */
	public void setContractId(Integer id);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setContractFileId
	 */
	public void setContractFileId(int id);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setContractFileId
	 */
	public void setContractFileId(Integer id);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setContractFile
	 */
	public void setContractFile(ICFile contractFile);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setPrognosis
	 */
	public void setPrognosis(String prognosis);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setPresentation
	 */
	public void setPresentation(String presentation);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setPreSchool
	 */
	public void setPreSchool(java.lang.String preSchool);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setMessage
	 */
	public void setMessage(String message);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setQueueOrder
	 */
	public void setQueueOrder(int order);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setApplicationStatus
	 */
	public void setApplicationStatus(char status);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setHasPriority
	 */
	public void setHasPriority(boolean hasPriority);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setHasDateSet
	 */
	public void setHasDateSet(boolean hasDateSet);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setHasQueuePriority
	 */
	public void setHasQueuePriority(boolean hasPriority);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setRejectionDateAsNull
	 */
	public void setRejectionDateAsNull(boolean setAsNull);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setHasExtraContract
	 */
	public void setHasExtraContract(boolean hasExtraContract);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setExtraContractMessage
	 */
	public void setExtraContractMessage(String message);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setHasExtraContractOther
	 */
	public void setHasExtraContractOther(boolean hasExtraContractOther);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setExtraContractMessageOther
	 */
	public void setExtraContractMessageOther(String message);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setRequestedCancelDate
	 */
	public void setRequestedCancelDate(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCancelMessage
	 */
	public void setCancelMessage(String message);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setParentalLeave
	 */
	public void setParentalLeave(boolean parentalLeave);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCancelFormFile
	 */
	public void setCancelFormFile(ICFile file);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCancelFormFileID
	 */
	public void setCancelFormFileID(int fileID);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCancelConfirmationReceived
	 */
	public void setCancelConfirmationReceived(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setFromDateRequested
	 */
	public void setFromDateRequested(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCancelRequestReceived
	 */
	public void setCancelRequestReceived(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#setCancelDateRequested
	 */
	public void setCancelDateRequested(Date date);

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#isAcceptedByParent
	 */
	public boolean isAcceptedByParent();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#isCancelledOrRejectedByParent
	 */
	public boolean isCancelledOrRejectedByParent();

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareApplicationBMPBean#isActive
	 */
	public boolean isActive();
}
