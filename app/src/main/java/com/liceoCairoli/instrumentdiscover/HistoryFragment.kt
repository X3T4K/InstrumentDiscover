package com.liceoCairoli.instrumentdiscover

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.liceoCairoli.instrumentdiscover.data.InstrumentViewModel
import com.liceoCairoli.instrumentdiscover.data.Instrument
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.history_list_element.view.*


class HistoryFragment : Fragment() {

    public lateinit var mUserViewModel: InstrumentViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_history, container, false)


        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(InstrumentViewModel::class.java)

        lv.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = InstrumentListAdapter()
            // UserViewModel
            mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { Instrument ->
                (adapter as InstrumentListAdapter).setData(Instrument)
            })
        }


    }




    class InstrumentListAdapter: RecyclerView.Adapter<InstrumentListAdapter.MyViewHolder>() {

        private var InstrumentList = emptyList<Instrument>()

        public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            return MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.history_list_element, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return InstrumentList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val currentItem = InstrumentList[position]
            holder.itemView.InstrumentName.text = currentItem.name
            holder.itemView.InstrumentName.setOnClickListener {

            }

            holder.itemView.deleteItem.setOnClickListener {
                val parentClass = MainActivity()
                parentClass.deleteInstrument(
                    Instrument(
                        0,
                        currentItem.name,
                        currentItem.ytLink,
                        currentItem.docLink
                    )
                )
                notifyDataSetChanged()
            }
        }

        public fun setData(user: List<Instrument>) {
            this.InstrumentList = user
            notifyDataSetChanged()
        }


    }


}
