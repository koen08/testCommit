package com.koen.wordreaderx

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myAssetManager = applicationContext.assets
        val textView: TextView = findViewById(R.id.textView)
        val files = myAssetManager.list("file")
            ?: throw RuntimeException("Files not found")

        val fileInputStream = myAssetManager.open("file/3.docx")

        val document = XWPFDocument(fileInputStream)
        val paragraphs: List<XWPFParagraph> = document.paragraphs
        val strBuilder = StringBuilder()
        for (para in paragraphs) {
            strBuilder.append(para.text + "\n")
        }
        fileInputStream.close()
        textView.text = strBuilder
    }
}