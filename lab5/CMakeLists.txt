cmake_minimum_required(VERSION 3.23)
project(lab5)

set(CMAKE_CXX_STANDARD 17)

add_executable(rsa_key
        RSA_KEY.CPP RSA_KEY_UTIL.h)

add_executable(rsa_key_file
        RSA_KEY_FILE.CPP RSA_KEY_UTIL.h)

add_custom_command(TARGET rsa_key_file PRE_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_directory
        ${CMAKE_SOURCE_DIR}/input/ $<TARGET_FILE_DIR:rsa_key_file>/input/)
