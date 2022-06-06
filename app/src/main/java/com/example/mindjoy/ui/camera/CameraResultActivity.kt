package com.example.mindjoy.ui.camera

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityCameraResultBinding
import com.example.mindjoy.ui.helper.rotateBitmap
import com.example.mindjoy.ui.helper.uriToFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class CameraResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraResultBinding

    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setImageFromCamera()
        setImageFromGallery()

        binding.btnRetake.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            finish()
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

        val result = rotateBitmap(
            BitmapFactory.decodeFile(myFile.path),
            isBackCamera
        )

        val bitmap: Bitmap = result

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
}