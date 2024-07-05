package org.firstinspires.ftc.teamcode.command;

public class Command {
    String name;
    public String getName(){
        return name;
    }
    public void setName(String new_name){
        name = new_name;
    }

    public void initialize(){}
    public void execute(){}
    public void end(){}
    public boolean isFinished(){
        return false;
    }

    public ParallelCommandGroup alongWith(Command... commands){
        ParallelCommandGroup group = new ParallelCommandGroup(this);
        group.addCommands(commands);

        return group;
    }

    public void schedule(){
        CommandSheduler.getInstance().shedule(this);
    }

    public void cancel(){
        CommandSheduler.instance.cancel(this);
    }


}
