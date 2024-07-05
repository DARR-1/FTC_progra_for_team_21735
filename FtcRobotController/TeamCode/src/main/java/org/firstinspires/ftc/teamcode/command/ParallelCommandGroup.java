package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.Utils.Constants;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ParallelCommandGroup extends Command{
    final Set<Command> commands = new LinkedHashSet<>();
    public ParallelCommandGroup(Command... commands){
        addCommands(commands);
    }

    public void addCommands(Command... commands){
        for (Command command : commands){
            if (command != null) {
                this.commands.add(command);
            }
            else {
                Constants.telemetry.addData("Status","Tried to add a null command");
            }
        }
    }

    public final void initialize(){
        for (Command command : commands){
            command.initialize();
        }
    }

    public final void execute(){
        for (Command command : commands){
            command.execute();
            if (command.isFinished()){
                command.end();
                commands.remove(command);
            }
        }
    }

    public final boolean isFinished(){
        if (commands.isEmpty()){
            return true;
        }
        return false;
    }
}
