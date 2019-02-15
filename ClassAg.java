import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class ClassAg {

	public static void main(String[] args) {
		try
		{
			Runtime rt = Runtime.instance();
			String[] arg1=new String[1];
			String[] arg2=new String[1];
			String[] arg3=new String[1];
			arg1[0]="ID3";
			arg2[0]="J48";
			arg3[0]="BayesNet";
			rt.setCloseVM(true);
			ProfileImpl p=new ProfileImpl("localhost", 1099, "RHSP");
			ContainerController mc=rt.createMainContainer(p);
			
			AgentController v=mc.createNewAgent("Station", "Station", arg1);
			
			v.start();

			AgentController a1=mc.createNewAgent("DataSourceController_ID1", "DataSourceController", arg1);
			a1.start();
			
			AgentController a2=mc.createNewAgent("DataSourceController_ID2", "DataSourceController", arg2);
			a2.start();
			
			AgentController a3=mc.createNewAgent("DataSourceController_ID3", "DataSourceController", arg3);
			a3.start();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}


	}

}
