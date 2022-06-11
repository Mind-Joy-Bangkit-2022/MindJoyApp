package com.example.mindjoy.ui.camera

import android.app.PendingIntent.getActivity
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
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.databinding.ActivityCameraResultBinding
import com.example.mindjoy.network.ApiConfig
import com.example.mindjoy.network.ApiService
import com.example.mindjoy.network.EmotionResult
import com.example.mindjoy.ui.helper.rotateBitmap
import com.example.mindjoy.ui.helper.uriToFile
import com.example.mindjoy.ui.viewmodel.CameraResultViewModel
import com.example.mindjoy.ui.viewmodel.LoginViewModel
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

    private var expressionStatus: String? = null

    private lateinit var viewModel: CameraResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar!!.visibility = View.GONE

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            CameraResultViewModel::class.java
        )

        setImageFromCamera()
//        setImageFromGallery()

        binding.btnRetake.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDetect.setOnClickListener {
            uploadImage()
            viewModel.isSuccessful.observe(this) { successful ->
                if (successful) {
                    viewModel.response.observe(this) {
                        expressionStatus = it.result
                    }
                    moveToResult()
                    viewModel.updateSuccessfulValue(false)
                }
            }
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
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

        viewModel.setExpression(imageMultipart)
    }

    private fun moveToResult(){
        Intent(this, ExpressionResultActivity::class.java).also {
            intent.putExtra("expressionStatus", expressionStatus)
            startActivity(it)
        }
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