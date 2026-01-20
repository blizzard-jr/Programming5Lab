package mumiytroll;

import Things.Jar;
import Things.ThingStatus;

public class Friend extends Trolls implements Minor_characters{
    protected static TrollsEmotion generalEmotion;

    @Override
    public void initiatorOfScream(){
        System.out.println(this.getName() + " закричал и все подхватили");
    }

    @Override
    public void initiatorOfFill(Jar jar) {
        System.out.println(this.getName() + " схватил банку из под варенья и они наполнили её волшебной водой");
        jar.setJarStat(ThingStatus.FILLED);
    }

    @Override
    public void initiatorOfBreak(Jar jar) {
        System.out.println(this.getName() + " свитснул и они разбили её об нос лодки");
        jar.setJarStat(ThingStatus.BROKEN);
    }

    public Friend(int x, int y, String name){
        super(x, y, name);
    }


    @Override
    public void goTravel() {
        System.out.println(this.getName() + " отправился в путешествие");
    }


}
