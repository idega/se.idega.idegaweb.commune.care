package se.idega.idegaweb.commune.care.importer;


public class NackaAfterSchoolFixFileHandlerHomeImpl extends com.idega.business.IBOHomeImpl implements NackaAfterSchoolFixFileHandlerHome
{
 @Override
protected Class getBeanInterfaceClass(){
  return NackaAfterSchoolFixFileHandler.class;
 }


 public NackaAfterSchoolFixFileHandler create() throws javax.ejb.CreateException{
  return (NackaAfterSchoolFixFileHandler) super.createIBO();
 }



}