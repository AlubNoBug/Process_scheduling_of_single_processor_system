package OS;

import java.util.ArrayList;
import java.util.Collections;



public class SchedulingMethod{
    private static  int n;

    public SchedulingMethod() {
        SchedulingMethod.n = 0;
    }

    public static int getN() {
        return SchedulingMethod.n;
    }

    public static void setN(int n) {
        SchedulingMethod.n = n;
    }



    public static Pcb[] createProcess(int n) {
        Pcb[] pcb = new Pcb[n];
        for(int i = 0; i < n; i++) {
            pcb[i] = new Pcb();
            pcb[i].setId(i);
            pcb[i].setName((int)(Math.random()*10)+i*10);
            pcb[i].setStatus(0);
            pcb[i].setPri((int)(Math.random()*10)+1);
            pcb[i].setTime((int)(Math.random()*10)+1);
            if(n!=1)
                pcb[i].setNext(i+1);
            else
                pcb[i].setNext(i);
            if(i == n-1)
                pcb[i].setNext(0);
        }
        return pcb;
    }

    public static void RR(Pcb[] pcb,int n) {
        ArrayList<Pcb> pcbList = new ArrayList<Pcb>();
        Pcb p;
        for(int i = 0; i < n; i++) {
            pcbList.add(pcb[i]);
        }
        while(!isFinish(pcbList)) {
            p = pcbList.get(0);
            p.setStatus(1);
            if(pcbList.size()!=1)
                p.setNext(pcbList.get(1).getId());
            else
                p.setNext(pcbList.get(0).getId());
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            p.setTime(p.getTime()-1);

            if (p.getTime() == 0) {
                p.setStatus(-1);
                p.setNext(0);
                pcbList.remove(0);
            }
            else {
                if(pcbList.size()!=1)
                    p.setStatus(0);
                pcbList.add(pcbList.get(0));
                pcbList.remove(0);
            }
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }






    public static void PSA(Pcb[] pcb,int n) {
        ArrayList<Pcb> pcbList = new ArrayList<Pcb>();
        Pcb p;
        for(int i = 0; i < n; i++) {
            pcbList.add(pcb[i]);
        }
        while(!isFinish(pcbList)) {
            Collections.sort(pcbList,new comparePri());
            p = pcbList.get(0);
            p.setStatus(1);
            if(pcbList.size()!=1)
                p.setNext(pcbList.get(1).getId());
            else
                p.setNext(pcbList.get(0).getId());
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            p.setPri(p.getPri()-1);
            p.setTime(p.getTime()-1);
            if (p.getTime() == 0) {
                p.setStatus(-1);
                p.setNext(0);
                pcbList.remove(0);
            }else {
                if(pcbList.size()!=1)
                    p.setStatus(0);
            }
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void SPN(Pcb[] pcb,int n) {
        ArrayList<Pcb> pcbList = new ArrayList<Pcb>();
        Pcb p;
        for(int i = 0; i < n; i++) {
            pcbList.add(pcb[i]);
        }
        Collections.sort(pcbList,new compareTime());
        while(!isFinish(pcbList)) {
            p = pcbList.get(0);
            p.setStatus(1);
            if(pcbList.size()!=1)
                p.setNext(pcbList.get(1).getId());
            else
                p.setNext(pcbList.get(0).getId());
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }


            p.setTime(p.getTime()-1);
            if (p.getTime() == 0) {
                p.setStatus(-1);
                p.setNext(0);
                pcbList.remove(0);
            }else {
                if(pcbList.size()!=1)
                    p.setStatus(0);
            }
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void SPT(Pcb[] pcb,int n) {
        ArrayList<Pcb> pcbList = new ArrayList<Pcb>();
        Pcb p;
        int rand;
        int i=1;
        int count = 0;
		/*for(int i = 0; i < n; i++) {
			rand = (int)(Math.random()*100);
			if(rand == 77)
				pcbList.add(pcb[i]);
		}*/
        pcbList.add(pcb[0]);
        while(!isFinish(pcbList)) {
            while(i<n && count != 2) {
                rand = (int)(Math.random()*100);
                if(rand == 77) {
                    pcbList.add(pcb[i]);
                    i++;
                    count++;
                }
            }
            count = 0;
            p = pcbList.get(0);
            p.setStatus(1);
            if(pcbList.size()!=1)
                p.setNext(pcbList.get(1).getId());
            else
                p.setNext(pcbList.get(0).getId());
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }

            p.setTime(p.getTime()-1);
            if (p.getTime() == 0) {
                p.setStatus(-1);
                p.setNext(0);
                pcbList.remove(0);
            }else {
                if(pcbList.size()!=1)
                    p.setStatus(0);
            }
            Label.setPP(p);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            Collections.sort(pcbList,new compareTime());
        }
    }

    public static boolean isFinish(ArrayList<Pcb> pcbList) {
        boolean flag = true;
        for(Pcb p:pcbList) {
            if(p.getTime() != 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }



}
