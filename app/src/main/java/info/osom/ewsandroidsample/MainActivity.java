package info.osom.ewsandroidsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import it.sephiroth.android.library.tooltip.Tooltip;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.setProperty("android.javax.xml.stream.XMLInputFactory", "com.sun.xml.stream.ZephyrParserFactory");
        System.setProperty("android.javax.xml.stream.XMLOutputFactory", "com.sun.xml.stream.ZephyrWriterFactory");
        System.setProperty("android.javax.xml.stream.XMLEventFactory", "com.sun.xml.stream.events.ZephyrEventFactory");
        showToolTip();
    }

    public void loadClass(View view) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String className = System.getProperty("android.javax.xml.stream.XMLOutputFactory");
        try {
            Class spiClass;
            if (classLoader == null) {
                spiClass = Class.forName(className);
            } else {
                spiClass = classLoader.loadClass(className);
            }
            Object o = spiClass.newInstance();
            Log.d("TEST", "Success");
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException x) {
            Log.e("TEST", "ClassNotFoundException", x);
            Toast.makeText(this, "ClassNotFoundException", Toast.LENGTH_LONG).show();
            throw new RuntimeException(x);
        } catch (Exception x) {
            Log.e("TEST", "Exception", x);
            Toast.makeText(this, "Exception", Toast.LENGTH_LONG).show();
            throw new RuntimeException(x);
        }
    }

    private void showToolTip(){
        Tooltip.make(MainActivity.this,
                new Tooltip.Builder(101)
                        .anchor(getWindow().getDecorView(), Tooltip.Gravity.BOTTOM)
                        .activateDelay(800)
                        .showDelay(300)
                        .text("Now click the button")
                        .build()
        ).show();
    }
}
