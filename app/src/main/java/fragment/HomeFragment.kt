package fragment

import Home.HomeGeneral
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import brand.Addbrand
import com.example.frippy_v_fin.R
import com.example.frippy_v_fin.login.Singup


class HomeFragment : Fragment() {

    private var boutique: Button?=null
    private var home: Button?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview= inflater.inflate(R.layout.fragment_home, container, false)
        boutique=rootview.findViewById(R.id.buttonboutique)
        home=rootview.findViewById(R.id.buttonhome)
        boutique!!.setOnClickListener{

            activity?.let { val intent = Intent(it,Addbrand::class.java)
            it.startActivity(intent)}

            }

        home!!.setOnClickListener{

            activity?.let { val intent = Intent(it,HomeGeneral::class.java)
                it.startActivity(intent)}

        }
        return rootview
        }

    }



