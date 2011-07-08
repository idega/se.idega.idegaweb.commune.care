package se.idega.idegaweb.commune.care.importer;


public class TabyPlacementImportFileHandlerHomeImpl extends com.idega.business.IBOHomeImpl implements TabyPlacementImportFileHandlerHome
{
 @Override
protected Class getBeanInterfaceClass(){
  return TabyPlacementImportFileHandler.class;
 }


 public TabyPlacementImportFileHandler create() throws javax.ejb.CreateException{
  return (TabyPlacementImportFileHandler) super.createIBO();
 }



}