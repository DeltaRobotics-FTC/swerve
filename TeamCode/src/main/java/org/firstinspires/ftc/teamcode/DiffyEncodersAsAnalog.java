package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;

public class DiffyEncodersAsAnalog implements HardwareDevice {
    private final DcMotorEx topEncoder;
    private final DcMotorEx bottomEncoder;
    private final double tpr;

    public DiffyEncodersAsAnalog(DcMotorEx topEncoder, DcMotorEx bottomEncoder, double tpr) {
        this.topEncoder = topEncoder;
        this.bottomEncoder = bottomEncoder;
        this.tpr = tpr;
    }
    
    public double getVoltage() {
        double ticks = (topEncoder.getCurrentPosition() - bottomEncoder.getCurrentPosition()) % tpr;
        ticks = ticks > 0 ? ticks : tpr + ticks;
        return (ticks / tpr) * 3.3;
    }

    public double getMaxVoltage() {
        return 3.3;
    }

    @Override
    public Manufacturer getManufacturer() {
        return null;
    }

    @Override
    public String getDeviceName() {
        return "";
    }

    @Override
    public String getConnectionInfo() {
        return "";
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {

    }

    @Override
    public void close() {

    }
}