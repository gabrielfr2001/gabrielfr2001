package br.com.scheid.mbean;
 
import java.util.ResourceBundle; 

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public abstract class AbstractCommonMBean {
	
	private ApplicationMBean application;
	private ResourceBundle bundle;
	
	public AbstractCommonMBean() {
		this.loadApplicationMBean();
	}
	
	public String getLabel(String labelName){
		this.loadBundle();
		return this.bundle.getString(labelName);
	}
	
	public void addMessage(String message, FacesMessage.Severity type){
		 FacesMessage facesMessage = new FacesMessage(message);
		 facesMessage.setSeverity(type);
		 FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public Boolean usaEstoque(){
		return this.application.getConfiguracoes().getUtilizarEstoque();
	}
	
	private void loadBundle(){
		if(this.bundle == null){
			this.bundle = ResourceBundle.getBundle(this.getBundleDir(), this.application.getLocale());
		}
	}
	
	private void loadApplicationMBean(){
		if(this.application == null){
			ELContext eLContext = FacesContext.getCurrentInstance().getELContext();
			this.application = (ApplicationMBean) eLContext.getELResolver().getValue(eLContext, null,"applicationMBean");
		}
	}
	
	public abstract String getBundleDir();
}
