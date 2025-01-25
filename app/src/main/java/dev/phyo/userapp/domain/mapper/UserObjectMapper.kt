package dev.phyo.userapp.domain.mapper

import dev.phyo.userapp.data.local.UserEntity
import dev.phyo.userapp.data.remote.model.User

internal fun List<UserEntity>.toDomainModel(): List<User>{
    return this.map { User(it.id, it.email, it.first_name, it.last_name, it.avatar) }
}

internal fun List<User>.toEntityModel(): List<UserEntity>{
    return this.map { UserEntity(it.id, it.email, it.first_name, it.last_name, it.avatar) }
}