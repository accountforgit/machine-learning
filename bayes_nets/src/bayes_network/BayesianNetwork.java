package bayes_network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import data.DataSet;
import data.attribute.Attribute;

public class BayesianNetwork 
{
	/**
	 * Sets verbose output on or off
	 */
	public boolean verbose = true;
	
	/**
	 * Network structure inference algorithms
	 */
	public static final int NAIVE_BAYES = 0;
	public static final int TAN = 1;
	
	/**
	 * The algorithm used to build the network
	 */
	private int netInference;
	
	Map<Attribute, BNNode> nodes;
		
	/**
	 * Constructor
	 */
	public BayesianNetwork()
	{
		this.nodes = new HashMap<Attribute, BNNode>();
	}
	
	public void setNetInference(int netInference)
	{
		this.netInference = netInference;
	}
	
	/**
	 * @return an ArrayList holding all Node objects in the network
	 */
	public ArrayList<BNNode> getNodes()
	{
		Collection<BNNode> col = nodes.values();
		return new ArrayList<BNNode>(col);
	}
	
	/**
	 * Add a new Node to the network
	 * 
	 * @param newNode the new Node
	 */
	public void addNode(BNNode newNode)
	{
		this.nodes.put(newNode.getAttribute(), newNode);
	}
	
	/**
	 * Retrieve a Node according to the Attribute this Node represents
	 * 
	 * @param attr the Attribute represented by the Node of interest
	 * @return the Node that represents this Attribute
	 */
	public BNNode getNode(Attribute attr)
	{
		return this.nodes.get(attr);
	}
	
	/**
	 * Create a directed edge in the network
	 * 
	 * @param parent the parent Node of the edge
	 * @param child the child Node of the edge
	 */
	public void createEdge(BNNode parent, BNNode child)
	{
		parent.addChild(child);
		child.addParent(parent);
	}
	
	/**
	 * Remove a directed edge from the network
	 * 
	 * @param parent the parent Node of the edge
	 * @param child the child Node of the edge
	 */
	public void removeConnection(BNNode parent, BNNode child)
	{
		parent.getChildren().remove(child);
		child.getParents().remove(parent);
	}

	@Override
	public String toString()
	{
		
		String result = "\n\n";
		
		// For each node, print its parents
		for (BNNode node : nodes.values())
		{	
			result += node.getAttribute().getName();
			
			for (BNNode parent : node.getParents())
			{
				result += " ";
				result += parent.getAttribute().getName();
			}
			result += "\n";
			
			if (verbose)
			{
				result += "\n\n";
				result += node.getCPD().toString();
				result += "\n";
			}
		}
		
		return result;
	}
	
	public double makeQuery()
	{
		return 0;
	}

	
	private String getTypeName()
	{
		String result = "";
		
		switch(netInference)
		{
		case(NAIVE_BAYES):
			result = "Naive Bayes";
			break;
		case(TAN):
			result = "TAN";
			break;
		}
		
		return result;
	}
		
	/**
	 * @param netInference the algorithm used to create the network's
	 * structure
	 */
	protected void setInference(int netInference)
	{
		this.netInference = netInference;
	}
	
}