roduction
======
Breeze is a scalable, visual, rule-supported modeling tool for software architecture (SA) based on Eclipse framework, which provides multi-views for architecture designers to create, edit and verify the SA of a system. Breeze is based on Breeze/ADL proposed by our group [citation], and can be applied to large, complex software systems. Breeze can capture both static architecture specifications and dynamic reconfigurations, in terms of Breeze/ADL definitions. Breeze provides symbolic features to model typical SA elements (e.g., component, connector and link). Based on this, the style (e.g., C-S) for SA can be verified and constrained. Moreover, Breeze supports the production definition to specify the architecture evolution. The production derives from the graph rewriting rules in the graph transformation. A production is divided into two parts, that is, left hand side (LHS) and right hand side (RHS). The LHS represents the precondition and RHS means the results. If there is a part of architecture model satisfying the LHS then this part should be replaced by the RHS. By leveraging the production, Breeze provides some classical operations, like addition and removal, to implement the architecture reconfiguration. Breeze consists of five modules: 


1. Editor provides text editor which enables creating and manipulating the architecture model, properties and productions. 
2. Parser includes model parser and rule parser. Model parser is used to parse the xml based Breeze/ADL document into internal representations. The rule parser parses each production into two internal representations - LHS and RHS.


3. Viewer provides user friendly viewers which include both design and explorer viewer for architect. 
4. Matcher is capable of checking if there exist some parts of the architecture model matching the LHS of a rule. 
5. Executor implements these dynamic operations.

Requirements
1. JDK 1.7.0
2. Eclipse for RCP and RAP Developers Version: Kepler Service Release 1

Installation
1. Here is the source code of Breeze, you can also download the executable version running on windows from: https://github.com/BreezeCSA/Breeze-executable-version.
2. To install Breeze, your computer need install JDK and Eclipse. We recommend you to install JDK 1.7.0 and Eclipse for RCP and RAP Developers Version: Kepler Service Release 1 to avoid version errors.
3. After the installation, you need to import five projects including: dom4j_test, org.jfree.jfreeForRCP, org.sjtu.radlab.breeze.diagram, org.sjtu.radlab.breeze.launch.macos, org.sjtu.radlab.breeze.model and org.sjtu.radlab.breeze.model.edit into Eclipse. Then run org.sjtu.radlab.breeze.diagram as Eclipse Application.
Architecture Modelling
4. In order to illustrate how Breeze models the static and dynamic aspects of the software architecture, we explain the key steps here.

Create a New Project
Click the File/New/Breeze Diagram on menu to initial a new project. The left Tree panel shows the corresponding view of the project. There are two important files in this project, that is, .breeze and .breeze_diagram. .breeze file is an XML version file, based on our Breeze/ADL, of the architecture model. .breeze_diagram file is a graphical notations of the architecture model.

Style Design
1. Create a Style: the next step of building an architecture is to choose a style for the architecture. Currently, we support Client/Server style. In the Palette, designer clicks the ‘Architecture style’ first, and then clicks on the design panel to create a new style. On the property editor, designer can choose the CS in drop-down list of ‘style type’. 
2. Create a Template Set: In the Palette, designer clicks the ‘Template’, and then clicks on ‘Style’ box on the design panel to create a new template set. On the property editor, designer can customize the properties for the template set, like template name.
3. Create a Template Node: In the Palette, designer clicks the ‘Template Node’, and then clicks on ‘Template’ box on the design panel to create a new template node. Designer chooses the node type for the template, like component type, connector type, from the property editor. Since the architecture follows the Client/Server style, the designer has to add an attribute ‘Template Type’ for the template and assigns one of three types of value, that is, client, server and connector. On the property editor, designer can customize the properties for the template node, like node name, node color.

Architecture Design
1. Create an Architecture: In the Palette, designer clicks the ‘Architecture’, and then clicks on the design panel to create a new architecture. On the property editor, designer can customize the properties for the architecture, like architecture name.
2. Create a component: In the Palette, designer clicks the ‘Component’, and then clicks on ‘Architecture’ box on the design panel to create a new component. Designer chooses the component type, created in the above step – Create a Template Node, for the component from the property editor. By right clicking the component, designer may create ‘port’ for it. On the property editor, designer can customize the properties for the port, like port direction, port type.
3. Create a connector: The processing of creating connector is similar to the component.
4. Create a link: In the Palette, designer clicks the ‘link’, and then clicks on ports of the component or connector to create a new connection. On the property editor, designer can customize the properties for the link node, like source port, target port.

Verification
As we mentioned before, Breeze currently supports the constraint verification including common constraint verification and style verification.
1. Common Constraint verification: There are some common constraints need to be satisfied. For example, port direction should match and the communication between the components should be through connector. To do the verification, designer needs to right click on the ‘.breeze’ file and chooses the ‘Constraint Check/Common Constraint Check’. If the architecture model does not pass the verification, Breeze will highlight the wrong elements and show the error messages.
2. Style Verification: Each architecture has its own style constraints. Currently, we support the Client/Server style verification. There are two constraints that the Client/Server style should subject to, i.e., clients cannot communication with each other directly and the direction of the request should from client to server.

Reconfiguration
The architecture reconfiguration is achieved through the production. Production can be divided into two parts, that is, left hand side (LHS) and right hand side (RHS). The LHS represents the precondition and RHS means the results, i.e., if there is a part of architecture model satisfying the LHS then this part should be replaced by the RHS. Leveraging the production, some operations, like addition and removal, can be applied to the software architecture, and implement the architecture reconfiguration.

To create a production, designer needs to right click the ‘Constraint Check/Initialize a production’. Then, two new files will be created at ‘production’ fold. Their names are automatically generated and which are like ‘xxx_production.breeze’ and ‘xxx_production.breeze_diagram’. Open the ’ xxx_production.breeze_diagram’, designer clicks the ‘production’ at the Palette and then clicks on ‘Style’ box (breeze reuses the style box for the production) on the design panel to create a new production. On the property editor, designer can customize the properties for the production, like production name. Each production includes two boxes, that is, LHS and RHS box. Designer needs to create two sub-architectures for the LHS and RHS box respectively. The LHS and RHS box use a set of components, connectors and links to form precondition and result separately. To apply a production, designer needs to right click the production box and click ‘Apply Production’. If part of the architecture model does match the LHS sub-architecture, Breeze will use RHS sub-architecture to replace the corresponding part.

Resources
1. Demo
A demo of Breeze is available at:
https://www.youtube.com/watch?v=nVYJZahZCAo&feature=youtu.be Note that the Breeze is not open-source. 

2. Slides
Slides describing the theoretical foundation, i.e., Breeze Graph Grammar and Breeze/ADL, of Breeze are available at:
1). https://docs.google.com/presentation/d/1qy0AJzHDqFkPYbfafOzwf_sLkESyEY7MfnIXGZ_6zNE/edit?usp=sharing 
2). https://docs.google.com/presentation/d/1H5-BUt0LDrus-ihsEOZOG3BesKUeKw-oU2nKK1MZ8tI/edit?usp=sharing

3. Papers
Our papers on Breeze Graph Grammar and Breeze/ADL are available at: 
1). http://www.computer.org/csdl/proceedings/compsac/2013/4987/00/4986a800-abs.html
2). http://ieeexplore.ieee.org/xpl/articleDetails.jsp?arnumber=6603687 



