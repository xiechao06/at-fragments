package com.xc.atfragments

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.xc.atfragments.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*

fun toast(context: Context, s: String) {
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}
fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction ) {
    beginTransaction().func().commit()
}

class MainActivity : AppCompatActivity(), HeadlinesFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {
        toast(this, item.content + "clicked")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (fragmentContainer != null) {
            // if restore from recovery, don't add fragment again
            if (savedInstanceState != null) {
                return
            }
            val fragment = HeadlinesFragment()
            fragment.arguments = intent.extras
            supportFragmentManager.inTransaction {
                add(fragmentContainer.id, fragment)
            }
        }
    }
}
