package com.example.hiroshi.godroidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import go.Go;
import go.prime.Prime;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText numberPicker = (EditText) this.findViewById(R.id.num);
        final TextView goresult = (TextView) this.findViewById(R.id.goresult);
        final TextView javaresult = (TextView) this.findViewById(R.id.javaresult);
        final Button goPrimeButton = (Button) this.findViewById(R.id.goprimebtn);
        goPrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final long n = Long.parseLong(numberPicker.getText().toString());
                final long start = System.currentTimeMillis();
                final long p = Prime.MaxPrime(n);
                final long end = System.currentTimeMillis();
                final StringBuffer sb = new StringBuffer();
                sb.append("Result: ").append(p).append(", Elapsed time: ").append(end - start).append("[msec]");
                goresult.setText(sb.toString());
            }
        });
        final Button javaPrimeButton = (Button) this.findViewById(R.id.javaprimebtn);
        javaPrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int n = Integer.parseInt(numberPicker.getText().toString());
                final long start = System.currentTimeMillis();
                final int p = maxPrimeNaive(n);
                final long end = System.currentTimeMillis();
                final StringBuffer sb = new StringBuffer();
                sb.append("Result: ").append(p).append(", Elapsed time: ").append(end - start).append("[msec]");
                javaresult.setText(sb.toString());
            }
        });
        Go.init(this.getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static int maxPrimeNaive(int n) {
        int[] primeNums = new int[n];
        int counter = 0;

        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= n; j++){
                if(i%j == 0 && i != j){
                    //breaks out of the loop since it is not prime
                    break;
                }else if(j == n){
                    primeNums[counter]= i;
                    //increase the counter in order to move through the array
                    counter++;
                }
            }
        }

        return primeNums[counter-1];
    }

    private static int maxPrimeSmart(int n) {
        // initially assume all integers are prime
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= n; i++) {

            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= n; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        // return max prime
        for (int i = n; i >= 2; i--) {
            if (isPrime[i]) return i;
        }
        return -1;
    }
}
