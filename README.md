Introduction
======
Breeze is a editable, visual, rule-supported modeling tool for software architecture (SA) based on Eclipse framework, which provides multi-views for architecture designers to create, edit and verify the SA of a system. Breeze is based on Breeze/ADL proposed by our group [citation], and can be applied to large, complex software systems. Breeze can capture both static architecture specifications and dynamic reconfigurations, in terms of Breeze/ADL definitions. Breeze provides symbolic features to model typical SA elements (e.g., component, connector and link). Based on this, the style (e.g., C-S) for SA can be verified and constrained. Moreover, Breeze is able to implement non-functional attribute analysis, including reliability analysis, safety analysis and correctness analysis on the architecture.

Breeze also supports the production definition to specify the architecture evolution. The production derives from the graph rewriting rules in the graph transformation. A production is divided into two parts, that is, left hand side (LHS) and right hand side (RHS). The LHS represents the precondition and RHS means the results. If there exist architectural specifications that satisfy LHS definitions, then this part will be replaced by the RHS definitions. By leveraging the production, Breeze provides some classical operations, like addition and removal, to implement the architecture reconfiguration. 

Requirements
======
1. JDK 1.7.0

2. Eclipse for RCP and RAP Developers Version: Kepler Service Release 1

Installation
======

To install Breeze, your computer need install JDK and Eclipse. We recommend you to install JDK 1.7.0 and Eclipse for RCP and RAP Developers Version: Kepler Service Release 1 to avoid version errors. Your Eclipse also need to install another framework, Graphical Modeling Framework (GMF). To use GMF, we need to install the GMF Tooling component; the fastest and easiest way to do this is to select (from main menu of our Eclipse installation) Help >> Install Modeling Components. 

![Image text](http://static.oschina.net/uploads/space/2012/1023/164709_UW3i_164134.png)

 In this screen, we choose all the options whose named begin with “GMF Modeling Framework”. We continue by clicking on “Finish”, accept the default settings and then we click “Restart Now” to complete the installation.
After restart the Eclipse, select File>>New>>Other and you will see:

![Image text](http://static.oschina.net/uploads/space/2012/1023/165223_Je1p_164134.png)

You can also see:http://www.rcp-vision.com/?p=1476&lang=en for GMF installation.

 After the installation, you need to import five projects including: dom4j_test, org.jfree.jfreeForRCP, org.sjtu.radlab.breeze.diagram, org.sjtu.radlab.breeze.launch.macos, org.sjtu.radlab.breeze.model and org.sjtu.radlab.breeze.model.edit into Eclipse. Then run org.sjtu.radlab.breeze.diagram as Eclipse Application.
 
Architecture Modelling
======
 In order to illustrate how Breeze models the static and dynamic aspects of the software architecture, we explain the key steps here.

Create a New Project
======
Click the File/New/Breeze Diagram on menu to initial a new project. The left Tree panel shows the corresponding view of the project. There are two important files in this project, that is, .breeze and .breeze_diagram. .breeze file is an XML version file, based on our Breeze/ADL, of the architecture model. .breeze_diagram file is a graphical notations of the architecture model.

Style Design
======
1. Create a Style: the next step of building an architecture is to choose a style for the architecture. Currently, we support Client/Server style. In the Palette, designer clicks the ‘Architecture style’ first, and then clicks on the design panel to create a new style. On the property editor, designer can choose the CS in drop-down list of ‘style type’. 

2. Create a Template Set: In the Palette, designer clicks the ‘Template’, and then clicks on ‘Style’ box on the design panel to create a new template set. On the property editor, designer can customize the properties for the template set, like template name.

3. Create a Template Node: In the Palette, designer clicks the ‘Template Node’, and then clicks on ‘Template’ box on the design panel to create a new template node. Designer chooses the node type for the template, like component type, connector type, from the property editor. Since the architecture follows the Client/Server style, the designer has to add an attribute ‘Template Type’ for the template and assigns one of three types of value, that is, client, server and connector. On the property editor, designer can customize the properties for the template node, like node name, node color.

Architecture Design
======
1. Create an Architecture: In the Palette, designer clicks the ‘Architecture’, and then clicks on the design panel to create a new architecture. On the property editor, designer can customize the properties for the architecture, like architecture name.

2. Create a component: In the Palette, designer clicks the ‘Component’, and then clicks on ‘Architecture’ box on the design panel to create a new component. Designer chooses the component type, created in the above step – Create a Template Node, for the component from the property editor. By right clicking the component, designer may create ‘port’ for it. On the property editor, designer can customize the properties for the port, like port direction, port type.

3. Create a connector: The processing of creating connector is similar to the component.

4. Create a link: In the Palette, designer clicks the ‘link’, and then clicks on ports of the component or connector to create a new connection. On the property editor, designer can customize the properties for the link node, like source port, target port.

Verification
======
As we mentioned before, Breeze currently supports the constraint verification including common constraint verification and style verification.

1. Common Constraint verification: There are some common constraints need to be satisfied. For example, port direction should match and the communication between the components should be through connector. To do the verification, designer needs to right click on the ‘.breeze’ file and chooses the ‘Constraint Check/Common Constraint Check’. If the architecture model does not pass the verification, Breeze will highlight the wrong elements and show the error messages.

2. Style Verification: Each architecture has its own style constraints. Currently, we support the Client/Server style verification. There are two constraints that the Client/Server style should subject to, i.e., clients cannot communication with each other directly and the direction of the request should from client to server.
 
Architecture analysis
======
To integrate non-funtional attribute analysis, a TrustConfiguration meta-model will first be generated based on Breeze/ADL architectural specifications. By right click on the ‘.breeze’ file, choose "Initialize model" and then choose an attribute to be analyzed.

![Image text](http://r.photo.store.qq.com/psb?/19ee4002-3d96-449b-b7f1-708fac6d26c0/NyFCC479UZOQ5G46YFVp1TePQCA0Qfz6MgHD0BoW7do!/o/dPAlIODtMwAA&ek=1&kp=1&pt=0&bo=sgSAAt4FIAMDAOk!&su=1212477457&sce=0-12-12&rf=2-9)

Currently, Breeze has integrated plugins that support following attributes: 

1.	Correctness checking: generating model checking specifications to detect deadlock or inconsistency problems in
the architecture design.

2.	Fault tree analysis (FTA): weaving safety elements defined in requirements into Breeze/ADL to implement analysis towards failure events.

3.	 Reliability analysis: mapping Breeze/ADL model to a Markov Chain model to conduct DTMC (Discrete-Time Markov Chain) reliability prediction.

Reconfiguration
======
The architecture reconfiguration is achieved through the production. Production can be divided into two parts, that is, left hand side (LHS) and right hand side (RHS). The LHS represents the precondition and RHS means the results, i.e., if there is a part of architecture model satisfying the LHS then this part should be replaced by the RHS. Leveraging the production, some operations, like addition and removal, can be applied to the software architecture, and implement the architecture reconfiguration.

![Image text](http://r.photo.store.qq.com/psb?/19ee4002-3d96-449b-b7f1-708fac6d26c0/5VNRw.NWWnDQg9LoIPPyQL8MPvcjwnh42WB3kmCxlr4!/o/dBTSJ.DpMwAA&ek=1&kp=1&pt=0&bo=sASAAt4FIQMDAOo!&su=1102686289&sce=0-12-12&rf=2-9)

To create a production, designer needs to right click the ‘Constraint Check/Initialize a production’. Then, two new files will be created at ‘production’ fold. Their names are automatically generated and which are like ‘xxx_production.breeze’ and ‘xxx_production.breeze_diagram’. Open the ’ xxx_production.breeze_diagram’, designer clicks the ‘production’ at the Palette and then clicks on ‘Style’ box (breeze reuses the style box for the production) on the design panel to create a new production. On the property editor, designer can customize the properties for the production, like production name. Each production includes two boxes, that is, LHS and RHS box. Designer needs to create two sub-architectures for the LHS and RHS box respectively. The LHS and RHS box use a set of components, connectors and links to form precondition and result separately. To apply a production, designer needs to right click the production box and click ‘Apply Production’. If part of the architecture model does match the LHS sub-architecture, Breeze will use RHS sub-architecture to replace the corresponding part.

Resources
======

Demo

A demo of Breeze is available at:. 

Slides

Slides describing the theoretical foundation, i.e., Breeze Graph Grammar and Breeze/ADL, of Breeze are available at:

1). https://docs.google.com/presentation/d/1qy0AJzHDqFkPYbfafOzwf_sLkESyEY7MfnIXGZ_6zNE/edit?usp=sharing 

2). https://docs.google.com/presentation/d/1H5-BUt0LDrus-ihsEOZOG3BesKUeKw-oU2nKK1MZ8tI/edit?usp=sharing

Papers

Our papers on Breeze Graph Grammar and Breeze/ADL are available at: 

1). http://www.computer.org/csdl/proceedings/compsac/2013/4987/00/4986a800-abs.html

2). http://ieeexplore.ieee.org/xpl/articleDetails.jsp?arnumber=6603687 



