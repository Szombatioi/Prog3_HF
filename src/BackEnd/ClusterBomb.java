package BackEnd;

@SuppressWarnings("serial")
public class ClusterBomb extends Bomb {

	public ClusterBomb(Board b) {
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.clusterBomb;
	}
	
	@Override
	protected void execute() {
//		master.addMoreBombs(1); //random szám legyen
		System.out.println("Cluster");
	}
	

}
