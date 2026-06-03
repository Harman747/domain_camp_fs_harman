public class EcoSmart_HUB {

    public static void main(String[] args) {

        SmartDevice[] devices = {
            new SmartCamera("1", "Camera", 15, true),
            new SmartLock("2", "Lock", 50, true),
            new SmartCamera("3", "TV", 10, false)
        };

        HomeHub hub = new HomeHub();
        hub.executeNightlyRoutine(devices);
    }
}

abstract class SmartDevice {
    //members
    protected String device_id;
    protected String deviceName;
    
    //method
    public abstract void runDiagnostic();

    //constructor
    protected SmartDevice(String id , String name){
        this.device_id = id;
        this.deviceName = name;
    }
}

interface BatteryOperated {
    int getBatteryLevel();
    void triggerRechargeAlert();

} 

class SmartCamera extends SmartDevice implements BatteryOperated{

    int BatterytLevel;
    boolean status;

    protected SmartCamera(String device_id , String deviceName , int BatterytLevel , boolean status){
        super(device_id, deviceName);
        this.BatterytLevel = BatterytLevel;
        this.status = status;
    }

    public int getBatteryLevel(){
        return BatterytLevel;
    }

    public void triggerRechargeAlert(){
        if(this.BatterytLevel < 20)
            System.out.println("Battery Level: " + getBatteryLevel() + " , please Charge device: " + super.deviceName);
            System.out.println();
    }

    public void runDiagnostic(){
        if(this.status == true){
            System.out.println("Device " + super.deviceName + " is working perfectly");
            System.out.println();
        }
        else{
            System.out.println("Device " + super.deviceName + " is not working properly");
            System.out.println();
        }
    }
}

class SmartLock  extends SmartDevice implements BatteryOperated{

    int BatterytLevel;
    boolean status;

    protected SmartLock (String device_id , String deviceName , int BatterytLevel , boolean status){
        super(device_id, deviceName);
        this.BatterytLevel = BatterytLevel;
        this.status = status;
    }

    public int getBatteryLevel(){
        return BatterytLevel;
    }

    public void triggerRechargeAlert(){
        if(this.BatterytLevel < 20)
            System.out.println("Battery Level: " + getBatteryLevel() + " , please Charge device: " + super.deviceName);
        System.out.println();
    }

    public void runDiagnostic(){
        if(this.status == true){
            System.out.println("Device " + super.deviceName + " is working perfectly");
            System.out.println();
        }
        else{
            System.out.println("Device " + super.deviceName + " is not working properly");
            System.out.println();
        }
    }
}


class HomeHub{
    public void executeNightlyRoutine(SmartDevice[] devices){
        for(SmartDevice s : devices){
            s.runDiagnostic();
            if(s instanceof BatteryOperated){
                ((BatteryOperated)s).triggerRechargeAlert();
            }
        }
    }
}