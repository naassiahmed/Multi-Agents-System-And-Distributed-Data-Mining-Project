import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.Stacking;

public class Station extends Agent {
	
	public void CheckSender(String s){
		
	}
	public void setup(){
		
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("DataSourceController_ID1",AID.ISLOCALNAME));
		msg.setContent("1");
		send(msg);
		
		 msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("DataSourceController_ID2",AID.ISLOCALNAME));
		msg.setContent("2");
		send(msg);
		
		 msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("DataSourceController_ID3",AID.ISLOCALNAME));
		msg.setContent("3");
		send(msg);
		
		FilteredClassifier[] set_classifiers=new FilteredClassifier[3];
		for(int nbAgents=0;nbAgents<3;nbAgents++){
			ACLMessage reply=blockingReceive();
			
			if(reply!=null)
				{
				try {
					Object[] resuls=(Object[]) reply.getContentObject();
					System.out.println(reply.getSender().getLocalName() + " --> "+resuls[1]);
					
					set_classifiers[nbAgents]=(FilteredClassifier) resuls[0];
					
				} catch (UnreadableException e) {
					
					e.printStackTrace();
				}
				
				}
			
		}
		
		try {
			System.out.println("Meta Classifiers has performance : "+ KnowledgeMining.MergeClassifiers(set_classifiers));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 
		
	

	}

}
