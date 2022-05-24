cmake_minimum_required(VERSION 3.17)
project(TestQt)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_PREFIX_PATH "/Volumes/D/qt-framework/5.15.2/clang_64/lib/cmake")
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)
set(QT_VERSION 5)
set(REQUIRED_LIBS Core Gui Widgets)
set(REQUIRED_LIBS_QUALIFIED Qt5::Core Qt5::Gui Qt5::Widgets)

add_executable(
        ${PROJECT_NAME}
        main.cpp
        validators/MovieValidator.h
        validators/MovieValidator.cpp
        exceptions/repository/RepositoryException.h
        exceptions/validators/MovieValidatorException.h
        file-handler/MovieFileRW.h
        file-handler/MovieFileRW.cpp
        tests/validators_tests.cpp
        tests/validators_tests.h
        domain/Entity.h
        domain/Entity.cpp
        domain/Movie.h
        domain/Movie.cpp
        repositories/MoviesRepository.h
        repositories/MoviesRepository.cpp
        services/ManagerService.cpp
        services/ManagerService.h
        console/Console.cpp
        console/Console.h
        tests/domain_tests.cpp
        tests/domain_tests.h
        tests/repository_tests.cpp
        tests/repository_tests.h
        tests/service_tests.cpp
        tests/service_tests.h
        services/UserService.h
        services/UserService.cpp
        data-display/DataDisplayHandler.h
        data-display/CSVDataDisplayHandler.h
        data-display/CSVDataDisplayHandler.cpp
        data-display/HTMLDataDisplayHandler.h
        data-display/HTMLDataDisplayHandler.cpp
        factory/DataDisplayFactory.h
        factory/DataDisplayFactory.cpp
        builders/HTMLBuilder.h
        ctml/ctml.hpp
        console/GuiApp.h
        console/GuiApp.cpp
        console/views/ChooseModeView.h
        console/views/ChooseModeView.cpp
        console/views/manager/MainScreen.cpp
        console/views/manager/MainScreen.h
        console/views/manager/AddMovieTab.h
        console/views/manager/AddMovieTab.cpp
        console/views/manager/UpdateMovieTab.cpp
        console/views/manager/UpdateMovieTab.h
        console/views/manager/ViewMoviesTab.cpp
        console/views/manager/ViewMoviesTab.cpp
        console/views/user/MainScreen.cpp
        console/views/user/MainScreen.h
        console/views/user/ViewWatchlistTab.cpp
        console/views/user/ViewWatchlistTab.h
        console/views/user/SearchTab.cpp
        console/views/user/SearchTab.h console/views/user/PreviewWatchlistTab.cpp console/views/user/PreviewWatchlistTab.h services/IUndoRedoService.h services/UndoRedoService.cpp services/UndoRedoService.h console/models/MovieModel.cpp console/models/MovieModel.h)

if (NOT CMAKE_PREFIX_PATH)
    message(WARNING "CMAKE_PREFIX_PATH is not defined, you may need to set it "
            "(-DCMAKE_PREFIX_PATH=\"path/to/Qt/lib/cmake\" or -DCMAKE_PREFIX_PATH=/usr/include/{host}/qt{version}/ on Ubuntu)")
endif ()

find_package(Qt${QT_VERSION} COMPONENTS ${REQUIRED_LIBS} REQUIRED)
target_link_libraries(${PROJECT_NAME} ${REQUIRED_LIBS_QUALIFIED})
