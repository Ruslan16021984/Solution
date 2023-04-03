package com.youarelaunched.challenge.data.repository.model

data class Vendor(
    val id: Int,
    val companyName: String,
    val coverPhoto: String,
    val area: String,
    val favorite: Boolean,
    val categories: List<VendorCategory>?,
    val tags: String?
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            companyName,
            companyName,
            "${companyName.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
