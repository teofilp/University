cmake_minimum_required(VERSION 3.17)
project(Lectures)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_PREFIX_PATH "/Volumes/D/qt-framework/5.15.2/clang_64/lib/cmake")
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

set(QT_VERSION 5)
set(REQUIRED_LIBS Core Gui Widgets)
set(REQUIRED_LIBS_QUALIFIED Qt5::Core Qt5::Gui Qt5::Widgets)

add_executable(${PROJECT_NAME} main.cpp
        Lecture_1/Arrays.c
        Lecture_1/Casting.cpp
        Lecture_1/CStrings.c
        Lecture_1/DataTypes.c
        Lecture_1/Functions.cpp
        Lecture_1/HelloWorld.cpp
        Lecture_1/HelloWorldC.c
        Lecture_1/Pointers.c
        Lecture_1/ReadWrite.c
        Lecture_1/Statements.c
        Lecture_1/StructExample.c
        Lecture_10/genes/DialogGenesListWidget.cpp
        Lecture_10/genes/DialogGenesListWidget.h
        Lecture_10/genes/DialogGenesView.cpp
        Lecture_10/genes/DialogGenesView.h
        Lecture_10/genes/GeneRepository.cpp
        Lecture_10/genes/GeneRepository.h
        Lecture_10/genes/lecture11_demo.cpp
        Lecture_10/genes/lecture11_demo.h
        Lecture_10/genes/main.cpp
        Lecture_10/genes_custom_model/GeneRepository.cpp
        Lecture_10/genes_custom_model/GeneRepository.h
        Lecture_10/genes_custom_model/GenesTableModel.cpp
        Lecture_10/genes_custom_model/GenesTableModel.h
        Lecture_10/genes_custom_model/lecture11_demo_custom_models.cpp
        Lecture_10/genes_custom_model/lecture11_demo_custom_models.h
        Lecture_10/genes_custom_model/main.cpp
        Lecture_10/genes_custom_model/PaginatedGenesTableModel.cpp
        Lecture_10/genes_custom_model/PaginatedGenesTableModel.h
        Lecture_10/genes_custom_model/PictureDelegate.cpp
        Lecture_10/genes_custom_model/PictureDelegate.h
        Lecture_2/ConstPointers.c
        Lecture_2/DynamicArray.c
        Lecture_2/DynamicArray.h
        Lecture_2/DynamicMemoryManagement.cpp
        Lecture_2/DynamicMemoryManagementC.c
        Lecture_2/main.c
        Lecture_2/NullDanglingPointers.c
        Lecture_2/Planet.c
        Lecture_2/Planet.h
        Lecture_2/PointersToFunctions.c
        Lecture_3/vector_v1/Vector2D.cpp
        Lecture_3/vector_v1/Vector2D.h
        Lecture_3/vector_v2/Vector2D.cpp
        Lecture_3/vector_v2/Vector2D.h
        Lecture_3/vector_v3/DynamicVector.cpp
        Lecture_3/vector_v3/DynamicVector.h
        Lecture_3/vector_v3/Vector2D.cpp
        Lecture_3/vector_v3/Vector2D.h
        Lecture_3/c_plus.cpp
        Lecture_3/vector_ex1.cpp
        Lecture_3/vector_ex2.cpp
        Lecture_3/vector_ex3.cpp
        Lecture_4/DynamicVector.h
        Lecture_4/DynamicVector_demo.cpp
        Lecture_4/FunctionTemplate.cpp
        Lecture_4/Pair.h
        Lecture_4/stl_demo.cpp
        Lecture_5/Animal.cpp
        Lecture_5/Animal.h
        Lecture_5/demo.cpp
        Lecture_5/Dog.cpp
        Lecture_5/Dog.h
        Lecture_5/Penguin.cpp
        Lecture_5/Penguin.h
        Lecture_6_abstract_base_class/Animal.cpp
        Lecture_6_abstract_base_class/Animal.h
        Lecture_6_abstract_base_class/demo.cpp
        Lecture_6_abstract_base_class/Dog.cpp
        Lecture_6_abstract_base_class/Dog.h
        Lecture_6_abstract_base_class/Filter.h
        Lecture_6_abstract_base_class/Penguin.cpp
        Lecture_6_abstract_base_class/Penguin.h
        Lecture_6_abstract_base_class/Zoo.cpp
        Lecture_6_abstract_base_class/Zoo.h
        Lecture_6_polymorphism/Animal.cpp
        Lecture_6_polymorphism/Animal.h
        Lecture_6_polymorphism/demo.cpp
        Lecture_6_polymorphism/Dog.cpp
        Lecture_6_polymorphism/Dog.h
        Lecture_6_polymorphism/Penguin.cpp
        Lecture_6_polymorphism/Penguin.h
        Lecture_7_exceptions/main.cpp
        Lecture_7_exceptions/Spaceship.cpp
        Lecture_7_exceptions/Spaceship.h
        Lecture_7_exceptions/SpaceshipValidator.cpp
        Lecture_7_exceptions/SpaceshipValidator.h
        Lecture_7_files/main.cpp
        Lecture_7_files/Spaceship.cpp
        Lecture_7_files/Spaceship.h
        Lecture_7_streams/Animal.cpp
        Lecture_7_streams/Animal.h
        Lecture_7_streams/main.cpp
        Lecture_7_streams/Penguin.cpp
        Lecture_7_streams/Penguin.h
        Lecture_8/hello_world/lecture9_demo_hello_world.cpp
        Lecture_8/hello_world/lecture9_demo_hello_world.h
        Lecture_8/hello_world/main.cpp
        Lecture_8/qt_designer/lecture9_demo_qt_designer.cpp
        Lecture_8/qt_designer/lecture9_demo_qt_designer.h
        Lecture_8/qt_designer/main.cpp
        Lecture_8/qt_designer/programaticallydesignedwidget.cpp
        Lecture_8/qt_designer/programaticallydesignedwidget.hpp
        Lecture_8/widgets/lecture9_demo_widgets.cpp
        Lecture_8/widgets/lecture9_demo_widgets.h
        Lecture_8/widgets/main.cpp
        Lecture_9/drawing/Drawing.cpp
        Lecture_9/drawing/Drawing.h
        Lecture_9/drawing/main.cpp
        Lecture_9/genes/Gene.cpp
        Lecture_9/genes/Gene.h
        Lecture_9/genes/GenesGUI.cpp
        Lecture_9/genes/GenesGUI.h
        Lecture_9/genes/Lecture10_demo.cpp
        Lecture_9/genes/Lecture10_demo.h
        Lecture_9/genes/main.cpp
        Lecture_9/introspection/IntrospectionExample.cpp
        Lecture_9/introspection/IntrospectionExample.h
        Lecture_9/introspection/main.cpp
        Lecture_9/introspection/MyClass.cpp
        Lecture_9/introspection/MyClass.h
        Lecture_9/notepad/Lecture10_demo_Notepad.cpp
        Lecture_9/notepad/Lecture10_demo_Notepad.h
        Lecture_9/notepad/main.cpp
        Lecture_9/spinslider/Lecture10_demo_spin_slider.cpp
        Lecture_9/spinslider/Lecture10_demo_spin_slider.h
        Lecture_9/spinslider/main.cpp
        Lecture_10/genes/DialogGenesListView.ui
        Lecture_10/genes/DialogGenesListWidget.cpp
        Lecture_10/genes/DialogGenesListWidget.h
        Lecture_10/genes/DialogGenesListWidget.ui
        Lecture_10/genes/DialogGenesView.cpp
        Lecture_10/genes/DialogGenesView.h
        Lecture_10/genes/GeneRepository.cpp
        Lecture_10/genes/GeneRepository.h
        Lecture_10/genes/lecture11_demo.cpp
        Lecture_10/genes/lecture11_demo.h
        Lecture_10/genes/lecture11_demo.qrc
        Lecture_10/genes/lecture11_demo.ui
        Lecture_10/genes/main.cpp
        Lecture_10/genes_custom_model/Ecoli.jpg
        Lecture_10/genes_custom_model/GeneRepository.cpp
        Lecture_10/genes_custom_model/GeneRepository.h
        Lecture_10/genes_custom_model/Genes.txt
        Lecture_10/genes_custom_model/GenesTableModel.cpp
        Lecture_10/genes_custom_model/GenesTableModel.h
        Lecture_10/genes_custom_model/human.jpg
        Lecture_10/genes_custom_model/lecture11_demo_custom_models.cpp
        Lecture_10/genes_custom_model/lecture11_demo_custom_models.h
        Lecture_10/genes_custom_model/lecture11_demo_custom_models.qrc
        Lecture_10/genes_custom_model/lecture11_demo_custom_models.ui
        Lecture_10/genes_custom_model/lecture11_demo_custom_models_.ui
        Lecture_10/genes_custom_model/main.cpp
        Lecture_10/genes_custom_model/PaginatedGenesTableModel.cpp
        Lecture_10/genes_custom_model/PaginatedGenesTableModel.h
        Lecture_10/genes_custom_model/PictureDelegate.cpp
        Lecture_10/genes_custom_model/PictureDelegate.h)

if (NOT CMAKE_PREFIX_PATH)
    message(WARNING "CMAKE_PREFIX_PATH is not defined, you may need to set it "
            "(-DCMAKE_PREFIX_PATH=\"path/to/Qt/lib/cmake\" or -DCMAKE_PREFIX_PATH=/usr/include/{host}/qt{version}/ on Ubuntu)")
endif ()

find_package(Qt${QT_VERSION} COMPONENTS ${REQUIRED_LIBS} REQUIRED)
target_link_libraries(${PROJECT_NAME} ${REQUIRED_LIBS_QUALIFIED})
