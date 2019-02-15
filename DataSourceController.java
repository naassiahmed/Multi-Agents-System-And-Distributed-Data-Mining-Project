import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import weka.core.converters.ConverterUtils.DataSource;

public class DataSourceController extends Agent {
	
	
	private boolean state=true;
	private ACLMessage msg;
	
	
	public void setup()
	{
		Scanner Cl=new Scanner(System.in);
		Object[] args=getArguments();
		System.out.println("The Agent : "+ getLocalName() +" use the algorithm :" +args[0]);
		DataSource ds = null;
		
		
		try {
			
			ds = new DataSource("E:\\Mémoire Master SII\\Docs & datsets\\Downloaded Datasets\\tic-tac-toe.arff");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		while(state==true)
		{
			
			msg=blockingReceive();
			if (msg!=null) 
			{
				try {
					

					
					
					if(getLocalName().equalsIgnoreCase("DataSourceController_ID1"))
						{
						try {
							System.out.println("Path for Agent 1 : ");
							String path=Cl.nextLine();
							//ds = new DataSource("E:\\Mémoire Master SII\\Docs & datsets\\Downloaded Datasets\\tic-tac-toe.arff");
							ds=new DataSource(path);
						} catch (Exception e) {
						
							e.printStackTrace();
						}
						
						
						}
					
					if(getLocalName().equalsIgnoreCase("DataSourceController_ID2"))
					{
					try {
						System.out.println("Path for Agent 2 : ");
						String path=Cl.nextLine();
						
						ds=new DataSource(path);
						
						//ds = new DataSource("E:\\Mémoire Master SII\\Docs & datsets\\Downloaded Datasets\\tic-tac-toe2.arff");
					} catch (Exception e) {
					
						e.printStackTrace();
					}
					
					
					}
					
					
					if(getLocalName().equalsIgnoreCase("DataSourceController_ID3"))
					{
					try {
						System.out.println("Path for Agent 3 : ");
						String path=Cl.nextLine();
						
						ds=new DataSource(path);
						
						//ds = new DataSource("E:\\Mémoire Master SII\\Docs & datsets\\Downloaded Datasets\\tic-tac-toe3.arff");
					} catch (Exception e) {
					
						e.printStackTrace();
					}
					
					
					}
					
					Object[] result=KnowledgeMining.Start(ds, Integer.parseInt(msg.getContent()),getLocalName());

					
					ACLMessage reply= new ACLMessage(ACLMessage.INFORM);
					reply.addReceiver(new AID("Station",AID.ISLOCALNAME));	
					reply.setContentObject(result);
					
					send(reply);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
		}
		
	
	}
}
