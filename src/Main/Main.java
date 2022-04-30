package Main;

import java.awt.EventQueue;

import javax.swing.UnsupportedLookAndFeelException;

import Controller.Controller;
import View.View;

import javax.swing.UIManager;

public class Main {
    public static void main(final String[] args) throws Exception {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
            	Controller.readFile();
                try
                {
                    new View();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}