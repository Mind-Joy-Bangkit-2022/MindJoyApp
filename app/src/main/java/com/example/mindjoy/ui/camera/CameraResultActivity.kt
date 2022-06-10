package com.example.mindjoy.ui.camera

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mindjoy.databinding.ActivityCameraResultBinding
import com.example.mindjoy.network.ApiConfig
import com.example.mindjoy.network.ApiService
import com.example.mindjoy.network.EmotionResult
import com.example.mindjoy.ui.helper.rotateBitmap
import com.example.mindjoy.ui.helper.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class CameraResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraResultBinding

    private var getFile: File? = null
    private var result: Bitmap? = null

    private lateinit var client: Call<EmotionResult>

    private val _response = MutableLiveData<EmotionResult>()
    val response: LiveData<EmotionResult> = _response

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setImageFromCamera()
//        setImageFromGallery()

        binding.btnRetake.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDetect.setOnClickListener {
            uploadImage()
            response.observe(this) {
                Log.d(TAG, "onCreate: ${it.result}")
                Toast.makeText(this, it.result, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setImageFromCamera(){
        val myFile = intent.getSerializableExtra("picture") as File
        val isBackCamera = intent.getSerializableExtra("isBackCamera") as Boolean

        result = rotateBitmap(
            BitmapFactory.decodeFile(myFile.path),
            isBackCamera
        )

        val bitmap: Bitmap = result as Bitmap

        val os: OutputStream = BufferedOutputStream(FileOutputStream(myFile))
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
        os.close()

        getFile = myFile
        binding.previewImageView.setImageBitmap(result)
    }

    private fun setImageFromGallery(){
        val selectedImg = intent.getSerializableExtra("file") as Uri
        val myFile = uriToFile(selectedImg, this)
        getFile = myFile
        binding.previewImageView.setImageURI(selectedImg)
    }

    private fun uploadImage(){
        val file = reduceFileImage(getFile as File)

        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "img",
            file.name,
            requestImageFile
        )

        client = ApiConfig.getApiService2().emotion(imageMultipart)
        client.enqueue(object : Callback<EmotionResult> {
            override fun onResponse(call: Call<EmotionResult>, response: Response<EmotionResult>) {
                _response.postValue(response.body())
            }

            override fun onFailure(call: Call<EmotionResult>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun reduceFileImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > 1000000)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

    private fun showLoading(state: Boolean){
        binding.progressBar?.visibility = if(state) View.VISIBLE else View.GONE
    }
}