package bayes_network.cpd;

import bayes_network.BNQuery;
import data.attribute.AttributeSet;

public class CPDTree 
{
	protected static Integer totalInstances;
	
	protected AttributeSet attributeSet;
	
	protected CPDNode root;
	
	
	public CPDNode getRoot()
	{
		return root;
	}
	
	/**
	 * Print the subtree rooted at the input Node to standard output.
	 * 
	 * @param root the node that roots the subtree being printed
	 */
	public String toString()
	{
		String result = "";
		
		for (CPDNode child : root.getChildren())
		{
			ToStringHelper treePrinter = new ToStringHelper(this.attributeSet);
			result += treePrinter.getString(child, 0);
		}
		
		return result;
	}
	
	/**
	 * A private helper class used for traversing the tree recursively
	 * in order to convert the tree to a String
	 */
	private static class ToStringHelper
	{
		@SuppressWarnings("unused")
		private AttributeSet attributeSet;
		
		public ToStringHelper(AttributeSet attributeSet)
		{
			this.attributeSet = attributeSet;
		}
		
		public String getString(CPDNode node, Integer depth)
		{
			String result = "";
			
			// Print the indentated "|" characters
			for (int i = 0; i < depth; i++)
			{
				result += "|     ";
			}
			
			// Print the value at the current node
			result += node;
	
			result += "\n";
			
			for (CPDNode child : node.getChildren())
			{
				result += getString(child, depth + 1);
			}	
			
			return result;
		}
	}
	
	public Double query(BNQuery query)
	{
		return this.root.calculateProbability(query);
	}
}