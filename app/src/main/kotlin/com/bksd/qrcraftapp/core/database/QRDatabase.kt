package com.bksd.campusechojournal.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bksd.campusechojournal.core.database.echo.EchoEntity
import com.bksd.campusechojournal.core.database.echo.FloatListTypeConverter
import com.bksd.campusechojournal.core.database.echo.MoodTypeConverter
import com.bksd.campusechojournal.core.database.echo.dao.EchoDao
import com.bksd.campusechojournal.core.database.relation_echo_topic.EchoTopicCrossRef
import com.bksd.campusechojournal.core.database.topic.TopicEntity

@Database(
    entities = [EchoEntity::class, TopicEntity::class, EchoTopicCrossRef::class],
    version = 1
)

@TypeConverters(
    MoodTypeConverter::class,
    FloatListTypeConverter::class
)
abstract class EchoDatabase : RoomDatabase() {
    abstract val echoDao: EchoDao
}