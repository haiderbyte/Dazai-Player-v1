package com.dazai.player.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NowPlayingScreen(
    title: String,
    artist: String,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 🌌 خلفية ضبابية ديناميكية مأخوذة من روح الألبوم (Glassmorphism Effect)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1E1E2C), Color(0xFF0F0F14))
                    )
                )
        )

        // محتوى الشاشة الرئيسي
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 💿 غلاف الألبوم بحواف دائرية ناعمة وظل خفيف
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .size(320.dp)
                    .aspectRatio(1f)
            ) {
                // هنا نضع صورة الغلاف الافتراضية أو المجلوبة
                Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 🎵 اسم الأغنية والفنان
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = artist,
                fontSize = 16.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 🎛️ شريط تقدم الأغنية (Slider)
            var sliderPosition by remember { mutableStateOf(0f) }
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White.copy(alpha = 0.8f),
                    inactiveTrackColor = Color.White.copy(alpha = 0.2f)
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ⏯️ أزرار التحكم بالتشغيل
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onPreviousClick, modifier = Modifier.size(48.dp)) {
                    Text("⏮", color = Color.White, fontSize = 24.sp)
                }
                
                Spacer(modifier = Modifier.width(32.dp))

                // زر التشغيل والإيقاف المؤقت الدائري الكبير
                FilledIconButton(
                    onClick = onPlayPauseClick,
                    modifier = Modifier.size(72.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = if (isPlaying) "⏸" else "▶",
                        color = Color.Black,
                        fontSize = 28.sp
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                IconButton(onClick = onNextClick, modifier = Modifier.size(48.dp)) {
                    Text("⏭", color = Color.White, fontSize = 24.sp)
                }
            }
        }
    }
}
