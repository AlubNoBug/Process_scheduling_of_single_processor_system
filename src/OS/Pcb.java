package OS;
import java.util.Comparator;
import java.util.Random;

public class Pcb  {
    private int id;
    private int name;
    private int status;
    private int pri;
    private int time;
    private int next;

    public Pcb() {
        this.id = 0;
        this.name = 0;
        this.status = 0;
        this.pri = 0;
        this.time = 0;
        this.next = 0;
    }

    public Pcb(int id,int name,int status,int pri,int time,int next) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.pri = pri;
        this.time = time;
        this.next = next;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPri(int pri) {
        this.pri = pri;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public void setNext(int next) {
        this.next = next;
    }

    public  int getId() {
        return this.id;
    }

    public  int getName() {
        return this.name;
    }

    public int getStatus() {
        return this.status;
    }

    public int getPri() {
        return this.pri;
    }

    public int getTime() {
        return this.time;
    }


    public int getNext() {
        return this.next;
    }

}

class comparePri implements Comparator<Pcb>{
    public int compare(Pcb o1,Pcb o2) {
        if(o1.getPri() > o2.getPri()) {
            return -1;
        }
        else if(o1.getPri() < o2.getPri()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

class compareTime implements Comparator<Pcb>{
    public int compare(Pcb o1,Pcb o2) {
        if(o1.getTime() > o2.getTime()) {
            return 1;
        }
        else if(o1.getTime() < o2.getTime()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
