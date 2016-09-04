package com.company;

import java.util.Observable;
import java.util.Observer;

public class Main {

    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        observable.addObserver(new MyObserver(String.class));
        System.out.println("Observers: " +observable.countObservers());
        observable.addObserver(new MyObserver(Integer.class));
        System.out.println("Observers: " +observable.countObservers());
        observable.changeMe("Pippo");
        observable.changeMe(new Integer(1));
    }

    private static class MyObservable extends Observable {

        public void changeMe(Object arg) {
            super.setChanged();
            notifyObservers(arg);
        }
    }

    private static class MyObserver implements Observer {

        private final Class<?> OBSERVED_TYPE;

        public MyObserver(Class<?> OBSERVED_TYPE) {
            this.OBSERVED_TYPE = OBSERVED_TYPE;
            System.out.println(this + " observer " + OBSERVED_TYPE);
        }

        /**
         * This method is called whenever the observed object is changed. An
         * application calls an <tt>Observable</tt> object's
         * <code>notifyObservers</code> method to have all the object's
         * observers notified of the change.
         *
         * @param o   the observable object.
         * @param arg an argument passed to the <code>notifyObservers</code>
         */
        public void update(Observable o, Object arg) {
            if (arg.getClass().equals(OBSERVED_TYPE)) {
                printNotification(o, arg);
            }
        }

        private void printNotification(Observable o, Object arg) {
            System.out.print(this + " Observable: " + o.toString() + "arg: " + arg.toString());
        }
    }


}
