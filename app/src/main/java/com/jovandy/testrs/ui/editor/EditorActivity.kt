package com.jovandy.testrs.ui.editor

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jovandy.testrs.R
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.activity_signin.progress_bar


class EditorActivity : AppCompatActivity(), EditorView {

    private lateinit var editorPresenter: EditorPresenter
    lateinit var name:String
    lateinit var id:String
    lateinit var lat:String
    lateinit var longt:String
    lateinit var lay:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editorPresenter = EditorPresenter(this, this)
        lay = getIntent().getStringExtra("lay").toString()
        Log.e("cek data", lay)
        if(lay.equals("1")){
            getSupportActionBar()?.setTitle("Update");
            id = getIntent().getStringExtra("id").toString()
            name =  getIntent().getStringExtra("nama").toString()
            lat = getIntent().getStringExtra("lat").toString()
            longt =  getIntent().getStringExtra("longt").toString()
            Log.e("cek data", name)
            editname.setText(name)
            Submit.setOnClickListener(){
                editorPresenter.DoUpdate(id, editname.text.toString(), lat, longt)
            }
        }else{
            getSupportActionBar()?.setTitle("Tambah");
            name =  getIntent().getStringExtra("nama").toString()
            lat = getIntent().getStringExtra("lat").toString()
            longt =  getIntent().getStringExtra("longt").toString()
            Log.e("cek data", lat)
            Submit.setOnClickListener(){
                editorPresenter.DoCreate(editname.text.toString(), lat, longt)
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_editor, menu)
        val item = menu.findItem(R.id.delete)
        if(!lay.equals("1")){
            item?.setVisible(false)
        }

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Initialize option to open catalogue settings.
            R.id.delete -> {
                editorPresenter.DoDelete(id)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSetProgressBarVisibility(visibility: Int) {
        progress_bar.visibility = visibility
    }

    override fun showError(message: String) {

    }
}