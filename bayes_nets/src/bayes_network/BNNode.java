package bayes_network;

import java.util.ArrayList;

import bayes_network.cpd.CPDQuery;
import bayes_network.cpd.CPDTree;

import data.attribute.Attribute;

/**
 * A single node in a {@code BayesianNetwork} object
 * 
 * @author Matthew Bernstien - matthewb@cs.wisc.edu
 */
public class BNNode 
{	
	/**
	 * This nodes parents
	 */
	private ArrayList<BNNode> parents;
	private ArrayList<BNNode> children;
	
	/**
	 * The Conditional Probability Distribution at this Node
	 */
	protected CPDTree cpd;
	
	/**
	 * Unique integer ID
	 */
	private int nodeId;
	
	/**
	 * Attribute variable represented by this node
	 */
	private Attribute nodeAttribute;

	/**
	 * Constructor 
	 * 
	 * @param nodeAttribute the Attribute variable this Node represents in the
	 */
	public BNNode(Attribute nodeAttribute)
	{
		this.nodeAttribute = nodeAttribute;
		this.parents = new ArrayList<BNNode>();
		this.children = new ArrayList<BNNode>();
	}
	
	/**
	 * Add a parent Node to this Node's parents
	 * 
	 * @param parent the parent Node
	 */
	public void addParent(BNNode parent)
	{		
		this.parents.add(parent);
	}
	
	/**
	 * Add a child Node to this Node's parents
	 * 
	 * @param parent the parent Node
	 */
	public void addChild(BNNode child)
	{
		this.children.add(child);
	}
	
	/**
	 * @return this Node's parent Nodes
	 */
	public ArrayList<BNNode> getParents()
	{
		return this.parents;
	}
	
	/**
	 * @return this Node's children Nodes
	 */
	public ArrayList<BNNode> getChildren() 
	{
		return this.children;
	}
	
	/**
	 * @return this Node's unique integer ID
	 */
	public int getId()
	{
		return this.nodeId;
	}
	
	/**
	 * @return the Attribute that this Node represents
	 */
	public Attribute getAttribute()
	{
		return this.nodeAttribute;
	}
	
	/**
	 * @param cpd the CPDTree associated with this Node
	 */
	public void setCPDTree(CPDTree cpd)
	{
		this.cpd = cpd;
	}
	
	/**
	 * @return the Conditional Probability Distribution (CPD) tree associated
	 * with this Node
	 */
	public CPDTree getCPD()
	{
		return this.cpd;
	}
	
	/**
	 * Query this node for a probability on its conditional probability tree
	 * 
	 * @param query the BNQuery object specifying the parameters of the query
	 * @return the probability of this query given by this node's conditional
	 * probability table
	 */
	public Double query(CPDQuery query)
	{
		return this.cpd.query(query);
	}
}
