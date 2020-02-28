package id.jason.submission2.model.repository

import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.model.dao.FavouriteDao

class FavouriteRepository (private val favouriteDao:FavouriteDao){

    val allFavouriteList: List<ShowsDetail> = favouriteDao.getAllFavouriteList()
}