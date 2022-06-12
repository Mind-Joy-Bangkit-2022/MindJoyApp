package com.example.mindjoy.ui.camera

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.databinding.ActivityCameraResultBinding
import com.example.mindjoy.ui.helper.rotateBitmap
import com.example.mindjoy.ui.helper.uriToFile
import com.example.mindjoy.ui.viewmodel.CameraResultViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*

class CameraResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraResultBinding

    private var getFile: File? = null
    private var result: Bitmap? = null

    private lateinit var viewModel: CameraResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar!!.visibility = View.GONE

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            CameraResultViewModel::class.java
        )

        val isFromGallery = intent.getBooleanExtra("isFromGallery", false)

        if (isFromGallery) {
            setImageFromGallery()
        } else {
            setImageFromCamera()
        }

        binding.btnRetake.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDetect.setOnClickListener {
            uploadImage()
            viewModel.isFailed.observe(this) { failed ->
                if (!failed) {
                    viewModel.response.observe(this) {
                        val expressionStatus = it
                        val intent = Intent(this, ExpressionResultActivity::class.java)
                        intent.putExtra("expressionStatus", expressionStatus)
                        startActivity(intent)
                    }
                    viewModel.updateSuccessfulValue(false)
                } else {
                    Toast.makeText(this, "Failed to upload, please try again!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.isLoading.observe(this)
        {
            showLoading(it)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateSuccessfulValue(false)
    }

    private fun setImageFromCamera() {
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

    private fun setImageFromGallery() {
        val uri = intent.getStringExtra("file")
        val selectedImg = Uri.parse(uri)

        val myFile = uriToFile(selectedImg, this)
        getFile = myFile
        binding.previewImageView.setImageURI(selectedImg)
    }

    private fun uploadImage() {
        val file = reduceFileImage(getFile as File)

        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "img",
            file.name,
            requestImageFile
        )

        viewModel.setExpression(imageMultipart)
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

    private fun showLoading(state: Boolean) {
        binding.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }
}