package gates;
public class Testing {

	public static void main(String[] args) {
		GateManipulator A = new GateManipulator();
		RunwayManipulator B = new RunwayManipulator();
		
		A.addingGates();
		A.chaos();
		B.addingRunways();
		B.chaos();
		
		if (A.gateIsEmpty(A.a4)){
			System.out.println("a4 is empty.");
		}
		else {
			System.out.println("a4 is full.");
		}
		
		int index = 3;
		System.out.println("a4 has the plane number " + A.a4.getPlaneID() + " in the gate");
		A.updatePID(1, 4);
		System.out.println("a4 has the plane number " + A.gateList.get(index).getPlaneID() + " in the gate");
		A.updatePID(2, 5);
		index = 4;
		System.out.println("a5 has the plane number " + A.gateList.get(index).getPlaneID() + " in the gate");
		
		if (B.runwayIsEmpty(B.r1)){
			System.out.println("Runway1 is empty.");
		}
		else {
			System.out.println("Runway1 is full.");
		}
		
		index = 0;
		System.out.println("Runway1 has the plane number " + B.runwayList.get(index).getPlaneID() + " in the gate");
		B.updatingRunways("R1", 3);
		System.out.println("Runway1 has the plane number " + B.runwayList.get(index).getPlaneID() + " in the gate");

	}
}
