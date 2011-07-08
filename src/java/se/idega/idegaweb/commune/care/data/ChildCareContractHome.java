/**
 * 
 */
package se.idega.idegaweb.commune.care.data;

import java.sql.Date;
import java.util.Collection;
import javax.ejb.FinderException;
import com.idega.block.school.data.School;
import com.idega.block.school.data.SchoolClassMember;
import com.idega.data.IDOException;
import com.idega.data.IDOHome;
import com.idega.user.data.User;
import com.idega.util.TimePeriod;


/**
 * <p>
 * TODO Dainis Describe Type ChildCareContractHome
 * </p>
 *  Last modified: $Date: 2006/03/05 16:54:49 $ by $Author: dainis $
 * 
 * @author <a href="mailto:Dainis@idega.com">Dainis</a>
 * @version $Revision: 1.13.2.1 $
 */
public interface ChildCareContractHome extends IDOHome {

	public ChildCareContract create() throws javax.ejb.CreateException;

	public ChildCareContract findByPrimaryKey(Object pk) throws javax.ejb.FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByChild
	 */
	public Collection findByChild(int childID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByChild
	 */
	public Collection findByChild(int childID, int resultSize, int startIndex) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByChildAndDateRange
	 */
	public Collection findByChildAndDateRange(User child, Date startDate, Date endDate) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByChildAndProvider
	 */
	public Collection findByChildAndProvider(int childID, int providerID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByApplication
	 */
	public Collection findByApplication(int applicationID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindValidContractByApplication
	 */
	public ChildCareContract findValidContractByApplication(int applicationID, Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindValidContractByPlacement
	 */
	public ChildCareContract findValidContractByPlacement(SchoolClassMember member, Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindValidContractByProvider
	 */
	public Collection findValidContractByProvider(int providerID, Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindApplicationByContract
	 */
	public ChildCareContract findApplicationByContract(int contractID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindValidContractByChild
	 */
	public ChildCareContract findValidContractByChild(int childID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindValidContractByChild
	 */
	public ChildCareContract findValidContractByChild(int childID, Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindContractByChildAndPeriod
	 */
	public ChildCareContract findContractByChildAndPeriod(User child, TimePeriod period) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindLatestTerminatedContractByChild
	 */
	public ChildCareContract findLatestTerminatedContractByChild(int childID, Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindNextContractByChild
	 */
	public ChildCareContract findNextContractByChild(ChildCareContract contract) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindNextTerminatedContractByChild
	 */
	public ChildCareContract findNextTerminatedContractByChild(ChildCareContract contract) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindPreviousTerminatedContractByChild
	 */
	public ChildCareContract findPreviousTerminatedContractByChild(ChildCareContract contract) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindPreviousTerminatedContractByContract
	 */
	public ChildCareContract findPreviousTerminatedContractByContract(ChildCareContract contract)
			throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindNextContractByContract
	 */
	public ChildCareContract findNextContractByContract(ChildCareContract contract) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindLatestContractByChild
	 */
	public ChildCareContract findLatestContractByChild(int childID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindLatestContractByApplication
	 */
	public ChildCareContract findLatestContractByApplication(int applicationID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindLatestByApplication
	 */
	public Collection findLatestByApplication(int applicationID, int maxNumberOfContracts) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindLatestNotByApplication
	 */
	public ChildCareContract findLatestNotByApplication(int applicationID, Date startDate) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindFirstContractByApplication
	 */
	public ChildCareContract findFirstContractByApplication(int applicationID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindContractByApplicationAndDate
	 */
	public ChildCareContract findContractByApplicationAndDate(int applicationID, Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindFutureContractsByApplication
	 */
	public Collection findFutureContractsByApplication(int applicationID, Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetNumberOfActiveNotWithProvider
	 */
	public int getNumberOfActiveNotWithProvider(int childID, int providerID) throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetNumberOfActiveNotWithProvider
	 */
	public int getNumberOfActiveNotWithProvider(int childID, int providerID, Date date) throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetNumberOfActiveForApplication
	 */
	public int getNumberOfActiveForApplication(int applicationID, Date date) throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetNumberOfTerminatedLaterNotWithProvider
	 */
	public int getNumberOfTerminatedLaterNotWithProvider(int childID, int providerID, Date date) throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetFutureContractsCountByApplication
	 */
	public int getFutureContractsCountByApplication(int applicationID, Date date) throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetContractsCountByApplication
	 */
	public int getContractsCountByApplication(int applicationID) throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetContractsCountByDateRangeAndProvider
	 */
	public int getContractsCountByDateRangeAndProvider(Date startDate, Date endDate, int providerID)
			throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByContractFileID
	 */
	public ChildCareContract findByContractFileID(int contractFileID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByDateRange
	 */
	public Collection findByDateRange(Date startDate, Date endDate) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindChangedSinceDate
	 */
	public Collection findChangedSinceDate(Date date) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindChangedBetween
	 */
	public Collection findChangedBetween(Date from, Date to) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByDateRangeAndProviderWhereStatusActive
	 */
	public Collection findByDateRangeAndProviderWhereStatusActive(Date startDate, Date endDate, School school)
			throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByDateRangeWhereStatusActive
	 */
	public Collection findByDateRangeWhereStatusActive(Date startDate, Date endDate) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindBySchoolClassMember
	 */
	public ChildCareContract findBySchoolClassMember(SchoolClassMember placement) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindLatestBySchoolClassMember
	 */
	public ChildCareContract findLatestBySchoolClassMember(SchoolClassMember placement) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindAll
	 */
	public Collection findAll() throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByInvoiceReceiver
	 */
	public Collection findByInvoiceReceiver(Integer invoiceReceiverID) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindByInvoiceReceiverActiveOrFuture
	 */
	public Collection findByInvoiceReceiverActiveOrFuture(Integer invoiceReceiverID, Date fromDate)
			throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindAllBySchoolClassMember
	 */
	public Collection findAllBySchoolClassMember(SchoolClassMember member) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbHomeGetCountBySchoolClassMember
	 */
	public int getCountBySchoolClassMember(SchoolClassMember member) throws IDOException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindAllByCareTimeAndDates
	 */
	public Collection findAllByCareTimeAndDates(String careTime, Date startingBefore, Date fromBirthDate,
			Date toBirthDate) throws FinderException;

	/**
	 * @see se.idega.idegaweb.commune.care.data.ChildCareContractBMPBean#ejbFindAllByProviderAndClassMemberDateRange
	 */
	public Collection findAllByProviderAndClassMemberDateRange(Integer schoolId, Date startFrom, Date startTo,
			Date endFrom, Date endTo) throws FinderException;
}
