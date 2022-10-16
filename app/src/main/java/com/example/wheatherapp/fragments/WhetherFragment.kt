package com.example.wheatherapp.fragments

import android.graphics.drawable.Drawable
import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.wheatherapp.R
import com.example.wheatherapp.adapters.WhetherAdapter
import com.example.wheatherapp.databinding.FragmentWhetherBinding
import com.example.wheatherapp.models.WhetherModel
import com.example.wheatherapp.models.api_model.WhetherAPIModel
import com.example.wheatherapp.models.services.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class WhetherFragment : Fragment() {
    val args: WhetherFragmentArgs by navArgs()

    lateinit var postList: MutableList<WhetherAPIModel>

    private var _binding: FragmentWhetherBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWhetherBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.weeklyWheatherRecyclerview.adapter =
            WhetherAdapter(
                listOf(
                    WhetherModel(
                        "WAITING",
                        ResourcesCompat.getDrawable(resources, R.drawable._0d, null)!!,
                        0.0,
                        0.0
                    ),

                    )
            )

        val apiInterface = ApiInterface.create(args.apiKey).getMovies()

        apiInterface.enqueue(object : Callback<WhetherAPIModel> {
            override fun onResponse(
                call: Call<WhetherAPIModel>?,
                response: Response<WhetherAPIModel>?
            ) {
                if (response!!.body() != null && response.code() == 200) {

                    val temp_max: Double = fahrenheitToCelsius(response.body()!!.main.temp_max)
                    val temp_min: Double = fahrenheitToCelsius(response.body()!!.main.temp_min)

                    val precision = DecimalFormat("0.00")
                    binding.todayDegreeText.text = precision.format(temp_max)
                    //Resim kısmında hata aldığım için statik bir resim gösteriyorum
                    binding.weeklyWheatherRecyclerview.adapter =
                        WhetherAdapter(
                            listOf(
                                WhetherModel("Bugün", null, temp_max, temp_min),
                            ),
                        )

                } else {
                    showError()

                }
            }

            override fun onFailure(call: Call<WhetherAPIModel>, t: Throwable) {
                showError()
            }
        })

    }

    fun fahrenheitToCelsius(degree: Double): Double {
        return degree - 273
        return (degree - 32) * 5 / 9
    }

    fun showError() {
        binding.todayDegreeText.text = "HATA"
        binding.weeklyWheatherRecyclerview.adapter =
            WhetherAdapter(
                listOf(
                    WhetherModel("HATA", null, 0.00, 0.00),
                ),
            )
    }


}