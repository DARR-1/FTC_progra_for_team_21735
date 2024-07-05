package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utils.Constants;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class CommandSheduler {

    static CommandSheduler instance;
    final static Set<Command> toSchedule = new LinkedHashSet<>();

    public static synchronized CommandSheduler getInstance(){
        if (instance == null){
            instance = new CommandSheduler();
        }
        return  instance;
    }
    public void shedule(Command... commands){
        for (Command command : commands){
            if (command == null){
                Constants.telemetry.addData("Status", "Tried to schedule a null command.");
                return;
            }

            toSchedule.add(command);
        }
    }

    public void run(){
        for (Command command : toSchedule){
            command.initialize();
            while (!command.isFinished()) {
                command.execute();
            }
            command.end();
        }
    }

    public void cancel(Command... commands){
        for (Command command : commands){
            if (command != null) {
                toSchedule.remove(command);
            } else {
                Constants.telemetry.addData("Status", "Tried to remove a null command.");
            }
        }

    }
}
