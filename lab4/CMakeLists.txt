cmake_minimum_required(VERSION 3.23)
project(lab4)

set(CMAKE_CXX_STANDARD 14)

add_executable(cipher
        CIPH.CPP)

add_executable(cipher_ascii
        CIPH_ASCII.CPP)

add_executable(decipher
        DECIPH.CPP)

add_executable(decipher_ascii
        DECIPH_ASCII.CPP)

add_executable(cipher_file
        CIPH_FILE.CPP)

add_executable(decipher_file
        DECIPH_FILE.CPP)

add_executable(cipher_file_cbc
        CIPH_FILE_CBC.CPP)

add_executable(decipher_file_cbc
        DECIPH_FILE_CBC.CPP)

add_custom_command(TARGET cipher_file cipher_file_cbc PRE_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_directory
        ${CMAKE_SOURCE_DIR}/input/ $<TARGET_FILE_DIR:cipher_file>/input/)

file(COPY data DESTINATION ${CMAKE_BINARY_DIR})
